package com.fenghuangzhujia.eshop.core.rlmessage;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

/**
 * 短信发送错误时抛出此异常。
 * @author pc
 *
 */
public class SendMessageException extends ErrorCodeException {

	private static final long serialVersionUID = 4130482730550961789L;
	
	public SendMessageException(String message, Throwable cause) {
		super(SystemErrorCodes.MESSAGE_SEND_ERROR,message, cause);
	}

	public SendMessageException(String message) {
		super(SystemErrorCodes.MESSAGE_SEND_ERROR,message);
	}

	public SendMessageException(Throwable cause) {
		super(SystemErrorCodes.MESSAGE_SEND_ERROR,cause);
	}
}
