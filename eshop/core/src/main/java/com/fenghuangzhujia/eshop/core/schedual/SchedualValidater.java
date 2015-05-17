package com.fenghuangzhujia.eshop.core.schedual;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.schedual.activity.Activity;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.validate.IValidater;

@Component
public class SchedualValidater implements IValidater<Schedual> {
	@Autowired
	private IValidater<Activity> validater;

	@Override
	public void validate(Schedual t) throws ErrorCodeException {
		Set<Activity> actives=t.getActives();
		if(actives==null) return;
		Set<Activity> uncheckedActives=new HashSet<>();
		uncheckedActives.addAll(actives);
		for (Activity active : actives) {
			validater.validate(active);
			uncheckedActives.remove(active);
			for (Activity toCheck : uncheckedActives) {
				if(toCheck.conflict(active))throw new ErrorCodeException(SystemErrorCodes.SCHEDUAL_CONFILICT);
			}			
		}
	}	
}
