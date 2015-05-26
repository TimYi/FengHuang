package com.fenghuangzhujia.eshop.web.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@ControllerAdvice
@ResponseBody
public class ApiExceptionHandler {
	
	@ExceptionHandler(value=ErrorCodeException.class)
	public String handleErrorCodeException(ErrorCodeException e) {
		return RequestResult.error(e.getErrorCode(), e.getMessage()).toJson();
	}
}
