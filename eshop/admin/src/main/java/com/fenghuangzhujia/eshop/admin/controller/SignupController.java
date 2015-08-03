package com.fenghuangzhujia.eshop.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.activity.signup.Signup;
import com.fenghuangzhujia.eshop.activity.signup.SignupService;
import com.fenghuangzhujia.foundation.core.rest.PagingController;

@RestController
@RequestMapping("signup")
public class SignupController extends PagingController<Signup, Signup> {

	@Autowired
	private SignupService service;
	
	public SignupService getService() {
		return service;
	}
}
