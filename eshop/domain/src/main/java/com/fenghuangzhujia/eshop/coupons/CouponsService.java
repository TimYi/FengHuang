package com.fenghuangzhujia.eshop.coupons;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.common.remind.impl.DtoUnreadRemindSpecificationService;
import com.fenghuangzhujia.eshop.coupons.dto.CouponsDto;
import com.fenghuangzhujia.eshop.coupons.dto.CouponsInputArgs;

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
