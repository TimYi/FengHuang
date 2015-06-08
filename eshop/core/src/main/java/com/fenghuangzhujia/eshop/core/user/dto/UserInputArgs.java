package com.fenghuangzhujia.eshop.core.user.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class UserInputArgs extends DtoBaseModel {

	private String username;
	private String password;
	private boolean verified;
	
	private String cnname;
	private String realName;
	private String ename;	
	private String mobile;
	private String intro;
	private String email;	
	private Date birthDay;	
	private String trade;
	private String address;
	
	//特殊传值字段
	private String sexId;
	private String bloodTypeId;
	private String constellationId;
	private MultipartFile avatarFile;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
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
	public String getSexId() {
		return sexId;
	}
	public void setSexId(String sexId) {
		this.sexId = sexId;
	}
	public String getBloodTypeId() {
		return bloodTypeId;
	}
	public void setBloodTypeId(String bloodTypeId) {
		this.bloodTypeId = bloodTypeId;
	}
	public String getConstellationId() {
		return constellationId;
	}
	public void setConstellationId(String constellationId) {
		this.constellationId = constellationId;
	}
	public MultipartFile getAvatarFile() {
		return avatarFile;
	}
	public void setAvatarFile(MultipartFile avatarFile) {
		this.avatarFile = avatarFile;
	}
}
