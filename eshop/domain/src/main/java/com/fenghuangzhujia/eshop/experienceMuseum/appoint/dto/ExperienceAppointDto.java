package com.fenghuangzhujia.eshop.experienceMuseum.appoint.dto;

import com.fenghuangzhujia.eshop.core.user.dto.UserDto;
import com.fenghuangzhujia.eshop.experienceMuseum.dto.ExperienceMuseumDto;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class ExperienceAppointDto extends DtoBaseModel {

	/**预约编号*/
	private String code;
	/**预约用户*/
	private UserDto user;
	/**用户预约的体验馆*/
	private ExperienceMuseumDto museum;
	/**用户真实姓名*/
	private String realName;
	/**用户手机号码*/
	private String mobile;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public ExperienceMuseumDto getMuseum() {
		return museum;
	}
	public void setMuseum(ExperienceMuseumDto museum) {
		this.museum = museum;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
