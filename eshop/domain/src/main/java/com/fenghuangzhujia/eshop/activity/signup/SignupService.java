package com.fenghuangzhujia.eshop.activity.signup;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;

@Service
@Transactional
public class SignupService extends DtoPagingService<Signup, Signup, Signup, String> {

	public List<Signup> query(String telephone) {
		return getRepository().query(telephone);
	}
	
	@Override
	public SignupRepository getRepository() {
		return (SignupRepository)super.getRepository();
	}
}
