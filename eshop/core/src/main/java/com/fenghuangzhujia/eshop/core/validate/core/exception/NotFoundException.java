package com.fenghuangzhujia.eshop.core.validate.core.exception;

public class NotFoundException extends ValidateException {

	private static final long serialVersionUID = -763348787653582512L;
	
	private static final String message="验证码错误";
	
	public NotFoundException(){
		super(message);
	}
	
	public NotFoundException(Throwable cause){
		super(message, cause);
	}
}
