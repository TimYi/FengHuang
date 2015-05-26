package com.fenghuangzhujia.eshop.core.validate.core.exception;

public class ExpireException extends ValidateException {

	private static final long serialVersionUID = 5326694133974523596L;
	
	private static final String message="验证码已过期，请重新输入";
	
	public ExpireException(){
		super(message);
	}
	
	public ExpireException(Throwable cause){
		super(message, cause);
	}
}
