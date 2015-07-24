package com.fenghuangzhujia.eshop.experienceMuseum.appoint;

import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Entity;

import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.experienceMuseum.ExperienceMuseum;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
@Table(name="fhzj_experience_appoint")
public class ExperienceAppoint extends UUIDBaseModel {

	public static final String TYPE_CODE="EA";
	
	/**预约编号*/
	private String code;
	/**预约用户*/
	private User user;
	/**用户预约的体验馆*/
	private ExperienceMuseum museum;
	/**用户真实姓名*/
	private String realName;
	/**用户手机号码*/
	private String mobile;
	/**预约状态*/
	private AppointStatus status=AppointStatus.WAITING;
	/**后台客服给用户的留言*/
	private String message;
	/**后台客服联系用户后，双方商定的到馆时间*/
	private Date appointTime;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@ManyToOne(optional=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne(optional=false)
	public ExperienceMuseum getMuseum() {
		return museum;
	}
	public void setMuseum(ExperienceMuseum museum) {
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

	public static enum AppointStatus {
		/**等待客服确认*/
		WAITING,
		/**客服已确认，准备到店*/
		PROCESSING,
		/**已经完成*/
		FINISH,
		/**已取消*/
		CANCEL;
	}
}
