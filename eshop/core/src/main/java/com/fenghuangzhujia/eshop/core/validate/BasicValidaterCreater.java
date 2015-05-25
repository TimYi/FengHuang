package com.fenghuangzhujia.eshop.core.validate;

import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.validate.core.Validater;
import com.fenghuangzhujia.eshop.core.validate.core.ValidaterCreater;
import com.fenghuangzhujia.eshop.core.validate.util.CodeUtil;

@Component
public class BasicValidaterCreater implements ValidaterCreater {
	private Integer charCount=4;
	private Integer expireMinutes=10;
	@Override
	public Validater create(String id) {
		String code=CodeUtil.getRandomNumber(charCount);
		BasicValidater validater=new BasicValidater(id, code, expireMinutes);
		return validater;
	}	
	
	@Override
	public Integer getExpireMinutes() {
		return this.expireMinutes;
	}
	
	@Override
	public void setExpireMinutes(Integer expireMinutes) {
		this.expireMinutes=expireMinutes;
	}
}
