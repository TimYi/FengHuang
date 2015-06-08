package com.fenghuangzhujia.eshop.coupons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.coupons.dto.CouponsDto;
import com.fenghuangzhujia.eshop.coupons.entity.CouponsEntity;
import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;

@Service
@Transactional
public class CouponsService extends DtoPagingService<CouponsEntity, CouponsDto, CouponsDto, String> {

	public List<CouponsDto> findUserCoupons(String userid) {
		List<CouponsEntity> list=getRepository().findByUserId(userid);
		Collection<CouponsDto> dtos=adapter.convertDoList(list);
		List<CouponsDto> result=new ArrayList<CouponsDto>();
		result.addAll(dtos);
		return result;
	}
	
	public List<CouponsDto> findUnUsedCoupons(String userid) {
		List<CouponsEntity> list=getRepository().findCouponsCouldUse(userid);
		Collection<CouponsDto> dtos=adapter.convertDoList(list);
		List<CouponsDto> result=new ArrayList<CouponsDto>();
		result.addAll(dtos);
		return result;
	}
	
	@Override
	public CouponsRepository getRepository() {
		return (CouponsRepository)super.getRepository();
	}
	
	@Autowired
	public void setCouponsRepository(CouponsRepository repository) {
		super.setRepository(repository);
	}
}
