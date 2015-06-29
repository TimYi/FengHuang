package com.fenghuangzhujia.eshop.core.commerce.coupons;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.commerce.coupons.dto.CouponsDto;
import com.fenghuangzhujia.eshop.core.commerce.coupons.dto.CouponsInputArgs;
import com.fenghuangzhujia.eshop.core.remind.impl.DtoUnreadRemindSpecificationService;

@Service
@Transactional
public class CouponsService extends DtoUnreadRemindSpecificationService<Coupons, CouponsDto, CouponsInputArgs, String> {

	public List<CouponsDto> findUnUsedCoupons(String userId) {
		List<Coupons> result=getRepository().findUnusedCoupons(userId);
		return adapter.convertDoList(result);
	}
	
	public List<CouponsDto> findUserCoupons(String userId) {
		List<Coupons> result=getRepository().findByUserId(userId);
		return adapter.convertDoList(result);
	}
	
	@Override
	public CouponsRepository getRepository() {
		return (CouponsRepository)super.getRepository();
	}
}
