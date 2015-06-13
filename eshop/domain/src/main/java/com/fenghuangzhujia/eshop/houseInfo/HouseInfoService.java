package com.fenghuangzhujia.eshop.houseInfo;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.houseInfo.dto.HouseInfoDto;
import com.fenghuangzhujia.eshop.houseInfo.dto.HouseInfoInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;

@Service
@Transactional
public class HouseInfoService extends DtoSpecificationService<HouseInfo, HouseInfoDto, HouseInfoInputArgs, String> {

	public List<HouseInfoDto> findByUser(String userid) {
		List<HouseInfo> list=getRepository().findByUserId(userid);
		List<HouseInfoDto> result=adapter.convertDoList(list);
		return result;
	}
	
	public HouseInfoDto findByUserAndId(String userId, String id) {
		HouseInfo info=getRepository().findOne(id);
		if(!info.getUser().getId().equals(userId))return null;
		return adapter.convertToDetailedDto(info);
	}
	
	public void deleteByUser(String userId, String id) {
		HouseInfo info=getRepository().findOne(id);
		if(!info.getUser().getId().equals(userId))return;
		getRepository().delete(info);
	}
	
	public HouseInfoDto editByUser(String userId, HouseInfoInputArgs args) {
		if(args==null) return null;
		String id=args.getId();
		HouseInfo d=getRepository().findOne(id);
		if(d==null) return null;
		if(!d.getUser().getId().equals(userId))return null;
		d=adapter.update(args, d);
		d=getRepository().save(d);
		HouseInfoDto result=adapter.convertToDetailedDto(d);
		return result;
	}
	
	public void setHouseInfoRepository(HouseInfoRepository repository) {
		super.setRepository(repository);
	}
	
	@Override
	public HouseInfoRepository getRepository() {
		return (HouseInfoRepository)super.getRepository();
	}
}
