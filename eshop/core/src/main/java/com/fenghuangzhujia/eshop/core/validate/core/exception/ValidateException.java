package com.fenghuangzhujia.eshop.core.validate.core.exception;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

/**
 * 所有验证错误抛出此异常，这个设计并不好，今后异常扩展很不方便。
 * @author pc
 *
 */
public abstract class ValidateException extends ErrorCodeException {

	private static final long serialVersionUID = -5949794225671524194L;
	
	public ValidateException(String message){
		super(SystemErrorCodes.VALIDATE_ERROR,message);
	}
	
	public ValidateException(Throwable cause){
		super(SystemErrorCodes.VALIDATE_ERROR,cause);
	}
	
	public ValidateException(String message, Throwable cause){
		super(SystemErrorCodes.VALIDATE_ERROR,message,cause);
	}
}
