package com.fenghuangzhujia.eshop.core.user.dto;

import java.util.Date;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.dics.dto.CategoryItemDto;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class UserDto extends DtoBaseModel {
	private String username;
	private boolean verified;
	
	private String cnname;
	private String realName;
	private String ename;
	private CategoryItemDto sex;
	private String mobile;
	private String intro;
	private String email;
	private String qqnum;
	private String qqid;
	private String weixinnum;
	private String weixinid;
	
	private Date birthDay;
	private CategoryItemDto bloodType;
	private CategoryItemDto constellation;
	private MediaContentDto avatar;
	private String trade;
	private String address;
	
	private Long expVal;
	private Long integra;
	private Date regTime;
	private String regIp;
	private Date loginTime;
	private String loginip;	
	private String weiboid;
	private boolean infoComplete;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String cnname) {
		this.cnname = cnname;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public CategoryItemDto getSex() {
		return sex;
	}
	public void setSex(CategoryItemDto sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQqnum() {
		return qqnum;
	}
	public void setQqnum(String qqnum) {
		this.qqnum = qqnum;
	}
	public String getQqid() {
		return qqid;
	}
	public void setQqid(String qqid) {
		this.qqid = qqid;
	}
	public String getWeixinnum() {
		return weixinnum;
	}
	public void setWeixinnum(String weixinnum) {
		this.weixinnum = weixinnum;
	}
	public String getWeixinid() {
		return weixinid;
	}
	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public CategoryItemDto getBloodType() {
		return bloodType;
	}
	public void setBloodType(CategoryItemDto bloodType) {
		this.bloodType = bloodType;
	}
	public CategoryItemDto getConstellation() {
		return constellation;
	}
	public void setConstellation(CategoryItemDto constellation) {
		this.constellation = constellation;
	}
	public MediaContentDto getAvatar() {
		return avatar;
	}
	public void setAvatar(MediaContentDto avatar) {
		this.avatar = avatar;
	}
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getExpVal() {
		return expVal;
	}
	public void setExpVal(Long expVal) {
		this.expVal = expVal;
	}
	public Long getIntegra() {
		return integra;
	}
	public void setIntegra(Long integra) {
		this.integra = integra;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public String getRegIp() {
		return regIp;
	}
	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoginip() {
		return loginip;
	}
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}
	public String getWeiboid() {
		return weiboid;
	}
	public void setWeiboid(String weiboid) {
		this.weiboid = weiboid;
	}
	public boolean getInfoComplete() {
		return infoComplete;
	}
	public void setInfoComplete(boolean infoComplete) {
		this.infoComplete = infoComplete;
	}
}
