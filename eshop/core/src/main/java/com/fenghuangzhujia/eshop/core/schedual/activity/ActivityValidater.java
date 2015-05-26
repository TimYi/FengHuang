package com.fenghuangzhujia.eshop.core.schedual.activity;

import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.validate.IValidater;

@Component
public class ActivityValidater implements IValidater<Activity> {

	@Override
	public void validate(Activity t) throws ErrorCodeException {
		if(t.getStartTime().compareTo(t.getEndTime())>=0) {
			throw new ErrorCodeException(SystemErrorCodes.ACTIVE_ILLEGAL);
		}		
	}
}
