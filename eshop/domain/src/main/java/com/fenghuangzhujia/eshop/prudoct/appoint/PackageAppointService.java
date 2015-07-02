package com.fenghuangzhujia.eshop.prudoct.appoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.area.Area;
import com.fenghuangzhujia.eshop.core.area.AreaRepository;
import com.fenghuangzhujia.eshop.core.area.Area.AreaLevel;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.commerce.couponsDef.CouponsAllocater;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.eshop.core.validate.message.MessageManager;
import com.fenghuangzhujia.eshop.prudoct.appoint.dto.PackageAppointDto;
import com.fenghuangzhujia.eshop.prudoct.appoint.dto.PackageAppointInputArgs;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackageRepository;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

@Service
@Transactional
public class PackageAppointService extends DtoSpecificationService<PackageAppoint, PackageAppointDto, PackageAppointInputArgs, String> {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DecoratePackageRepository decoratePackageRepository;
	@Autowired
	private MessageManager messageManager;
	@Autowired
	private AreaRepository areaRepository;
	@Autowired
	private PackageAppointValidater appointValidater;
	@Autowired
	private CouponsAllocater couponsAllocater;
	
	public PackageAppointDto appointByUser(PackageAppointInputArgs args) {
		PackageAppoint appoint=new PackageAppoint();
		User user=userRepository.findOne(args.getUserId());
		if(user==null)throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "用户id错误");
		//用户信息完整才能预约
		if(!user.getInfoComplete())
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "用户信息不完整，不能预约");
		appoint.setUser(user);
		
		//获取预约类型
		DecoratePackage decoratePackage=decoratePackageRepository.findOne(args.getDecoratePackageId()); 
		if(decoratePackage==null)
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "套餐id错误");
		appoint.setDecoratePackage(decoratePackage);
		//检测用户是否满足预约条件
		appointValidater.couldAppoint(user, decoratePackage);
		
		//暂时只允许用户用绑定手机号码预约，不能更换手机号。如果以后业务逻辑调整，这里要做相应调整。
		String mobile=user.getMobile();
		//检验短信验证码
		messageManager.validate(mobile, args.getValidater());
		appoint.setMobile(mobile);
		//使用用户绑定真实姓名，无视前端传来的参数
		String realName=user.getRealName();
		appoint.setRealName(realName);
		
		//获取用户选择的城市
		Area city=areaRepository.findOne(args.getCityId());
		if(city==null || !city.getLevel().equals(AreaLevel.CITY))
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "城市id错误");
		appoint.setCity(city);
		
		appoint=getRepository().save(appoint);
		
		//触发成功预约套餐分发优惠券事件
		couponsAllocater.allocate(CouponsAllocater.APPOINT_PACKAGE, user.getId());
		
		return adapter.convertToDetailedDto(appoint);
	}
	
	/**
	 * 获取限制规则下的未过期预约
	 * 预约可能已经被使用
	 * 当前预约限制为没人，每种套餐，每月一次。
	 * @param userId
	 * @param packageId
	 * @return
	 */
	public PackageAppoint getAliveAppoint(String userId, String packageId) {
		User user=userRepository.findOne(userId);
		DecoratePackage decoratePackage=decoratePackageRepository.findOne(packageId);
		return appointValidater.getAliveAppoint(user, decoratePackage);
	}

}
