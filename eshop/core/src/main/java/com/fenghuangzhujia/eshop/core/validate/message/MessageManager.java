package com.fenghuangzhujia.eshop.core.validate.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.validate.core.AbstractValidateManager;
import com.fenghuangzhujia.eshop.core.validate.core.exception.ValidateException;
import com.fenghuangzhujia.eshop.core.validate.dao.DaoValidaterService;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.utils.validater.PhoneNumberValidater;

/**
 * 管理短信发送
 * @author pc
 *
 */
@Component(value="messageManager")
public class MessageManager extends AbstractValidateManager {
	public MessageManager(){
		validaterCreater.setExpireMinutes(5);
	}
	
	@Override
	public void validate(String id, String code) throws ValidateException {
		if(!PhoneNumberValidater.isMobile(id))throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "请输入正确的11位手机号码");
		super.validate(id, code);
	}
	
	@Autowired
	public void setMessageSender(ValidateMessageSender credentialCreater) {
		super.setCredentialCreater(credentialCreater);
	}
	
	@Autowired
	public void setDaoValidaterService(DaoValidaterService validaterService) {
		super.setValidaterService(validaterService);
	}
}
