package com.fenghuangzhujia.eshop.core.validate;

import java.util.Calendar;
import java.util.Date;

import com.fenghuangzhujia.eshop.core.validate.core.Validater;
import com.fenghuangzhujia.eshop.core.validate.core.exception.ErrorCodeException;
import com.fenghuangzhujia.eshop.core.validate.core.exception.ExpireException;

public class BasicValidater implements Validater {
	
	private String id;
	private String code;
	private Date expireTime;
	
	public BasicValidater(String id, String code, Integer expireMinutes){
		this.id=id;
		this.code=code;
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.MINUTE, expireMinutes);
		Date date=calendar.getTime();
		this.expireTime=date;
	}
	
	@Override
	public void validate(String id, String code) {
		Date now=new Date();
		if(now.after(expireTime)){
			throw new ExpireException();
		}
		if(!(id.equals(this.id) || code.equals(this.code))){
			throw new ErrorCodeException();
		}
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the expireTime
	 */
	public Date getExpireTime() {
		return expireTime;
	}

	/**
	 * @param expireTime the expireTime to set
	 */
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
}
