package com.fenghuangzhujia.eshop.core.validate.core;

import org.springframework.beans.factory.annotation.Autowired;

import com.fenghuangzhujia.eshop.core.validate.BasicValidaterCreater;
import com.fenghuangzhujia.eshop.core.validate.core.exception.NotFoundException;
import com.fenghuangzhujia.eshop.core.validate.core.exception.ValidateException;

public abstract class AbstractValidateManager implements ValidateManager {
	/**
	 * 根据id产生验证凭据。
	 */
	protected ValidaterCreater validaterCreater=new BasicValidaterCreater();
	/**
	 * 保存和获取验证凭据
	 */
	protected ValidaterService validaterService;
	/**
	 * 负责验证信息的发送，生成。
	 */
	@Autowired
	protected CredentialCreater credentialCreater;
	
	@Override
	public Object create(String id) {
		Validater validater=validaterCreater.create(id);
		String code=validater.getCode();
		Integer expireMinutes=validaterCreater.getExpireMinutes();
		Object credential = credentialCreater.createCredential(id,code,expireMinutes);		
		validaterService.add(validater);
		return credential;
	}
	
	@Override
	public void validate(String id, String code) throws ValidateException {
		Validater validater=validaterService.get(id);
		if(validater==null){
			throw new NotFoundException();
		}
		validaterService.delete(id);
		validater.validate(id, code);
	}
	
	//以下为getters and setters	

	/**
	 * @param validaterCreater the validaterCreater to set
	 */
	@Autowired(required=false)
	public void setValidaterCreater(ValidaterCreater validaterCreater) {
		this.validaterCreater = validaterCreater;
	}
	/**
	 * @param validaterService the validaterService to set
	 */
	@Autowired
	public void setValidaterService(ValidaterService validaterService) {
		this.validaterService = validaterService;
	}
	/**
	 * @param credentialCreater the credentialCreater to set
	 */
	@Autowired
	public void setCredentialCreater(CredentialCreater credentialCreater) {
		this.credentialCreater = credentialCreater;
	}		
}
