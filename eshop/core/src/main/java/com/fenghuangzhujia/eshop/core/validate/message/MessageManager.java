package com.fenghuangzhujia.eshop.core.validate.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.validate.core.AbstractValidateManager;

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
	
	@Autowired
	public void setMessageSender(MessageSender credentialCreater) {
		super.setCredentialCreater(credentialCreater);
	}
}
