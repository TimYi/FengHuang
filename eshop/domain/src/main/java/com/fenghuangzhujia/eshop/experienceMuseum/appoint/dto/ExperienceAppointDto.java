package com.fenghuangzhujia.eshop.experienceMuseum.appoint.dto;

import java.util.Date;

import com.fenghuangzhujia.eshop.core.user.dto.UserDto;
import com.fenghuangzhujia.eshop.experienceMuseum.appoint.ExperienceAppoint.AppointStatus;
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
	/**预约状态*/
	private AppointStatus status;
	/**后台客服给用户的留言*/
	private String message;
	/**后台客服联系用户后，双方商定的到馆时间*/
	private Date appointTime;
	/**是否已读*/
	private boolean readed;
	
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
	public AppointStatus getStatus() {
		return status;
	}
	public void setStatus(AppointStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getAppointTime() {
		return appointTime;
	}
	public void setAppointTime(Date appointTime) {
		this.appointTime = appointTime;
	}
	public boolean getReaded() {
		return readed;
	}
	public void setReaded(boolean readed) {
		this.readed = readed;
	}
}
