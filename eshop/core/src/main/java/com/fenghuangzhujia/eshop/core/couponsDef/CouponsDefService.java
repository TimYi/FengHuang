package com.fenghuangzhujia.eshop.core.couponsDef;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.couponsDef.dto.CouponsDefDto;
import com.fenghuangzhujia.eshop.core.couponsDef.dto.CouponsDefInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;

@Service
@Transactional
public class CouponsDefService extends 
	DtoSpecificationService<CouponsDef, CouponsDefDto, CouponsDefInputArgs, String> {

	public CouponsDefDto findByEvent(String event) {
		CouponsDef def=getRepository().findByEvent(event);
		return adapter.convertToDetailedDto(def);
	}
	
	@Override
	public CouponsDefRepository getRepository() {
		return (CouponsDefRepository)super.getRepository();
	}
}
