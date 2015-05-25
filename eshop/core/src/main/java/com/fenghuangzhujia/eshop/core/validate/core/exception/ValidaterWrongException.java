package com.fenghuangzhujia.eshop.core.validate.core.exception;

public class ValidaterWrongException extends ValidateException {

	private static final long serialVersionUID = 7408716661634346607L;
	
	private static final String message="验证码错误";
	
	public ValidaterWrongException(){
		super(message);
	}
	
	public ValidaterWrongException(Throwable cause){
		super(message, cause);
	}
}
