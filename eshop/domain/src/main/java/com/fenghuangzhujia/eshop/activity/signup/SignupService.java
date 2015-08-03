package com.fenghuangzhujia.eshop.activity.signup;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;

@Service
@Transactional
public class SignupService extends DtoPagingService<Signup, Signup, Signup, String> {

}
