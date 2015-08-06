package com.fenghuangzhujia.eshop.prudoct.packages;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.commerce.order.GoodOrder.OrderStatus;
import com.fenghuangzhujia.eshop.commerce.order.GoodOrderRepository;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.eshop.prudoct.appoint.PackageAppoint;
import com.fenghuangzhujia.eshop.prudoct.appoint.PackageAppointValidater;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage.ScrambleStatus;
import com.fenghuangzhujia.eshop.prudoct.packages.dto.DecoratePackageDto;
import com.fenghuangzhujia.eshop.prudoct.packages.dto.DecoratePackageInputArgs;
import com.fenghuangzhujia.eshop.prudoct.packages.dto.DecoratePackageDto.PackageLifeCycle;
import com.fenghuangzhujia.eshop.prudoct.packages.space.DecorateSpaceService;
import com.fenghuangzhujia.eshop.prudoct.packages.space.dto.DecorateSpaceDto;
import com.fenghuangzhujia.eshop.prudoct.scramble.PackageGood;
import com.fenghuangzhujia.eshop.prudoct.scramble.PackageGoodRepository;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.media.MediaService;

@Service
@Transactional
public class DecoratePackageService extends DtoSpecificationService<DecoratePackage, DecoratePackageDto, DecoratePackageInputArgs, String> {
	
	@Autowired
	private MediaService mediaService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PackageAppointValidater appointValidater;
	@Autowired
	private PackageGoodRepository packageGoodRepository;
	@Autowired
	private DecorateSpaceService decorateSpaceService;
	@Autowired
	private GoodOrderRepository orderRepository;
	
	@Override
	public DecoratePackageRepository getRepository() {
		return (DecoratePackageRepository)super.getRepository();
	}
	
	@Autowired
	public void setDecoratePackageRepository(DecoratePackageRepository repository) {
		super.setRepository(repository);
	}
	
	public DecoratePackageDto findOne(String id, String userId) {
		if(StringUtils.isBlank(userId)) return findOne(id);
		User user=userRepository.findOne(userId);
		if(user==null) return findOne(id);
		DecoratePackage decoratePackage=getRepository().findOne(id);
		DecoratePackageDto result=getConverter(user).convert(decoratePackage);
		List<DecorateSpaceDto> spaces=decorateSpaceService.findByPackage(id);
		result.setSpaces(spaces);
		return result;
	}
	
	/**
	 * 为获取的列表附加用户能否预约等信息
	 * @param page
	 * @param size
	 * @param userId
	 * @return
	 */
	public PagedList<DecoratePackageDto> findPage(int page, int size, String userId) {
		//如果没有用户信息，正常返回列表
		if(StringUtils.isBlank(userId))
			return findPage(page, size);
		User user=userRepository.findOne(userId);
		if(user==null) {
			return findPage(page, size);
		}
		PageRequest request=new PageRequest(page-1, size);
		Page<DecoratePackage> list=getRepository().findAll(request);
		//根据用户预约状态，加入hasAppointed和couldAppoint以及reasonForCantAppoint信息
		Page<DecoratePackageDto> result=list.map(getConverter(user));
		return new PagedList<>(result);
	}
	
	private Converter<DecoratePackage, DecoratePackageDto> getConverter(User user) {
		return new Converter<DecoratePackage, DecoratePackageDto>() {
			@Override
			public DecoratePackageDto convert(DecoratePackage source) {
				PackageAppoint appoint=appointValidater.getAliveAppoint(user, source);
				DecoratePackageDto dto=adapter.convert(source);
				//检测用户预约状况
				if(appoint!=null && !appoint.isUsed()) {
					dto.setHasAppointed(true);
				} else {
					dto.setHasAppointed(false);
				}
				//判断用户是否可以预约
				if(appoint==null) {
					dto.setCouldAppoint(true);
				} else {
					dto.setCouldAppoint(false);
					dto.setReasonForCantAppoint("您在一个月内已经预约此套餐");
				}
				//判断用户是否已经预约过
			    List<PackageGood> good=packageGoodRepository.processingUserPackageOrder(user.getId(), source.getId());
			    if(good!=null && !good.isEmpty() ) {
			    	dto.setHasScrambled(true);
			    }
			    
			    PackageLifeCycle lifeCycle=null;			    
			    //开始判断套餐对用户的声明周期
			    if(dto.isHasAppointed()) {
			    	//已经预约
			    	ScrambleStatus status=dto.getStatus();
			    	if(status==ScrambleStatus.PREPARE) {
			    		lifeCycle=PackageLifeCycle.WAITING;
			    	} else if(status==ScrambleStatus.SCRAMBLE) {
						 lifeCycle=PackageLifeCycle.SCRAMBLE;
					} else {
						lifeCycle=PackageLifeCycle.FINISHED;
					}
			    } else if(dto.isCouldAppoint()) {
			    	//没预约且可以预约
			    	lifeCycle=PackageLifeCycle.APPOINT;
			    } else {
			    	//没预约且不能预约
			    	boolean shouldPay=orderRepository.countByUserIdAndStatus(user.getId(), OrderStatus.WAITING)>0;
			    	if(shouldPay) {
			    		lifeCycle=PackageLifeCycle.PAY;
			    	} else {
						lifeCycle=PackageLifeCycle.FINISHED;
					}
			    }
			    dto.setLifeCycle(lifeCycle);
			    
				return dto;
			}
		};
	}
}
