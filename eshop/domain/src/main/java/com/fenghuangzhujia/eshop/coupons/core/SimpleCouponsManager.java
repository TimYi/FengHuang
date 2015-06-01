package com.fenghuangzhujia.eshop.coupons.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.eshop.coupons.CouponsDefRepository;
import com.fenghuangzhujia.eshop.coupons.CouponsRepository;
import com.fenghuangzhujia.eshop.coupons.entity.CouponsDefEntity;
import com.fenghuangzhujia.eshop.coupons.entity.CouponsEntity;

@Component
@Transactional
public class SimpleCouponsManager implements CouponsManager {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CouponsDefRepository defRepository;
	@Autowired
	private CouponsRepository repository;

	@Override
	public Coupons allocate(String userid, String defid) {
		User user=userRepository.findOne(userid);
		CouponsDefEntity def=defRepository.findOne(defid);
		if(def==null)return null;
		CouponsEntity coupons=def.createCoupons();
		if(coupons==null)return null;
		coupons.setUser(user);
		coupons=repository.save(coupons);
		return coupons;
	}
}
