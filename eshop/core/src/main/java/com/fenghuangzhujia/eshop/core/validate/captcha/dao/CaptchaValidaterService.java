package com.fenghuangzhujia.eshop.core.validate.captcha.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.validate.BasicValidater;
import com.fenghuangzhujia.eshop.core.validate.core.Validater;
import com.fenghuangzhujia.eshop.core.validate.core.ValidaterService;

@Service(value="captchaValidaterService")
@Transactional
public class CaptchaValidaterService implements ValidaterService {

	@Autowired
	private CaptchaRepository repository;

	@Override
	public void add(Validater validater) {
		String id=validater.getId();
		CaptchaValidater entity;
		if(repository.exists(id)) {
			entity=repository.findOne(id);
		} else {
			entity=new CaptchaValidater();
			entity.setId(id);
		}	
		entity.setCode(validater.getCode());
		entity.setExpireTime(validater.getExpireTime());
		repository.save(entity);
	}

	@Override
	public Validater get(String id) {
		CaptchaValidater entity=repository.findOne(id);
		if(entity==null)return null;
		BasicValidater validater=new BasicValidater();
		validater.setId(entity.getId());
		validater.setExpireTime(entity.getExpireTime());
		validater.setCode(entity.getCode());
		validater.setId(entity.getId());
		return validater;
	}

	@Override
	public void delete(String id) {
		repository.delete(id);
	}
}
