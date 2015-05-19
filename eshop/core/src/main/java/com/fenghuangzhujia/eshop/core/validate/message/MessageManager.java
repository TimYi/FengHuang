package com.fenghuangzhujia.eshop.core.validate.message;

import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.validate.core.AbstractValidateManager;

/**
 * 管理短信发送
 * @author pc
 *
 */
@Component
public class MessageManager extends AbstractValidateManager {
	public MessageManager(){
		setCredentialCreater(new MessageSender());
		validaterCreater.setExpireMinutes(5);
	}
}
