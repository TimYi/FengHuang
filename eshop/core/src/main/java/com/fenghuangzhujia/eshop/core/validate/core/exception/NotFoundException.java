package com.fenghuangzhujia.eshop.core.validate.core.exception;

public class NotFoundException extends ValidateException {

	private static final long serialVersionUID = -763348787653582512L;
	
	private static final String message="凭据不存在";
	
	public NotFoundException(){
		super(message);
	}
	
	public NotFoundException(Throwable cause){
		super(message, cause);
	}
}
