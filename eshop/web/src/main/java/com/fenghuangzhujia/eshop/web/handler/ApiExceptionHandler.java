package com.fenghuangzhujia.eshop.web.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.utils.LogUtils;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@ControllerAdvice
@ResponseBody
public class ApiExceptionHandler {
	
	@ExceptionHandler(value=ErrorCodeException.class)
	public String handleErrorCodeException(ErrorCodeException e) {
		return RequestResult.error(e.getErrorCode(), e.getMessage()).toJson();
	}
	
	@ExceptionHandler(value=Exception.class)
	public String handleException(Exception e) {
		LogUtils.errorLog(e);
		return RequestResult.error(SystemErrorCodes.UNKNOWN_ERROR, e.getMessage()).toJson();
	}
}
