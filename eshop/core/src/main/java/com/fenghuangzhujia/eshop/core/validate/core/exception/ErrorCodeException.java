package com.fenghuangzhujia.eshop.core.validate.core.exception;

public class ErrorCodeException extends ValidateException {

	private static final long serialVersionUID = 7408716661634346607L;
	
	private static final String message="验证码错误";
	
	public ErrorCodeException(){
		super(message);
	}
	
	public ErrorCodeException(Throwable cause){
		super(message, cause);
	}
}
