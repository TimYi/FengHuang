package com.fenghuangzhujia.eshop.collect;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.ResourceType;
import com.fenghuangzhujia.eshop.artical.Artical;
import com.fenghuangzhujia.eshop.artical.ArticalRepository;
import com.fenghuangzhujia.eshop.collect.dto.CollectDto;
import com.fenghuangzhujia.eshop.collect.dto.CollectInputArgs;
import com.fenghuangzhujia.eshop.common.remind.impl.DtoUnreadRemindPagingService;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.eshop.prudoct.cases.DecorateCase;
import com.fenghuangzhujia.eshop.prudoct.cases.DecorateCaseRepository;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackageRepository;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

@Service
@Transactional
public class CollectService extends DtoUnreadRemindPagingService<Collect, CollectDto, CollectInputArgs, String> {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ArticalRepository articalRepository;
	@Autowired
	private DecoratePackageRepository packageRepository;
	@Autowired
	private DecorateCaseRepository caseRepository;
	
	public PagedList<CollectDto> findPage(int page, int size, String userid) {
		PageRequest request=new PageRequest(page-1, size);
		Page<Collect> list=getRepository().findByUserId(userid, request);
		Page<CollectDto> result=list.map(adapter);
		return new PagedList<>(result);
	}
	
	public CollectDto findOneByUser(String userid, String id) {
		Collect message=getRepository().findOne(id);
		if(!message.getUser().getId().equals(userid))throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "您只能获取自己的留言");
		return adapter.convertToDetailedDto(message);
	}
	
	public void deleteByUser(String userid, String id) {
		Collect message=getRepository().findOne(id);
		if(!message.getUser().getId().equals(userid))throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "您只能删除自己的留言");
		getRepository().delete(message);
	}
	
	@Autowired
	public void setCollectRepository(CollectRepository repository) {
		super.setRepository(repository);
	}
	
	@Override
	public CollectRepository getRepository() {
		return (CollectRepository)super.getRepository();
	}
	
	/**
	 * 保存不同类型的收藏
	 * @param userid
	 * @param sourceid
	 * @param type
	 * @param url
	 * @return
	 */
	public CollectDto add(String userid, String sourceid, ResourceType type, String url) {
		if(StringUtils.isBlank(sourceid) || type==null || StringUtils.isBlank(userid)){
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT);
		}
		Collect collect=new Collect();
		User user=userRepository.findOne(userid);
		collect.setUser(user);
		collect.setSourceid(sourceid);
		collect.setUrl(url);
		switch (type) {
		case ARTICAL:
			Artical artical=articalRepository.findOne(sourceid);
			collect.setName(artical.getTitle());
			collect.setType(ResourceType.ARTICAL);
			collect.setColumn("文章");
			collect.setMainPic(artical.getMainPic());
			break;
		case PACKAGE:
			DecoratePackage package1=packageRepository.findOne(sourceid);
			collect.setName(package1.getTitle());
			collect.setType(ResourceType.PACKAGE);
			collect.setColumn("装修套餐");
			collect.setMainPic(package1.getMainPic());
			break;
		case CASE:
			DecorateCase case1=caseRepository.findOne(sourceid);
			collect.setName(case1.getTitle());
			collect.setType(ResourceType.CASE);
			collect.setColumn("装修案例");
			collect.setMainPic(case1.getMainPic());
			break;
		default:
			break;
		}
		collect=getRepository().save(collect);
		return adapter.convert(collect);
	}
}
