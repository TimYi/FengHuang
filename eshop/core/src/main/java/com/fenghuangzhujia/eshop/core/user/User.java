package com.fenghuangzhujia.eshop.core.user;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fenghuangzhujia.eshop.core.authentication.authority.AbstractAuthority;
import com.fenghuangzhujia.eshop.core.authentication.role.Role;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.dics.CategoryItem;
import com.fenghuangzhujia.foundation.media.MediaContent;

@Entity
@Table(name="fhzj_user")
public class User extends UUIDBaseModel {
	private String username;	
	@JsonIgnore
	private String password;	
	private Set<Role> roles;
	private boolean verified;
	@JsonIgnore
	private String salt;
	private Set<AbstractAuthority> authorities;
	
	@ManyToMany
	@JoinTable
	public Set<AbstractAuthority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Set<AbstractAuthority> authorities) {
		this.authorities = authorities;
	}
	/**
	 * @return the username
	 */
	@Column(unique=true,updatable=false)
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="verified")
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	
	/**
	 * @return the roles
	 */
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="users")
	public Set<Role> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}	
	
	/**
	 * 加密盐
	 * @return
	 */
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}	

	//授权检测过程无关属性定义
	//目前用到的定义
	private String cnname;
	private String realName;
	private String ename;
	private CategoryItem sex;
	private String mobile;
	private String intro;
	private String email;
	private String qqnum;
	private String qqid;
	private String weixinnum;
	private String weixinid;
	
	private Date birthDay;
	private CategoryItem bloodType;
	private CategoryItem constellation;
	private MediaContent avatar;
	private String trade;
	private String address;
	
	private long expVal;
	private long integra;
	private Date regTime;
	private String regIp;
	private Date loginTime;
	private String loginip;	
	private String weiboid;

	/**
	 * 昵称
	 * @return
	 */
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String cnname) {
		this.cnname = cnname;
	}
	
	/**
	 * 真实姓名
	 * @return
	 */
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	/**
	 * 英文姓名
	 * @return
	 */
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	
	/**
	 * 性别
	 * @return
	 */
	@ManyToOne
	public CategoryItem getSex() {
		return sex;
	}
	public void setSex(CategoryItem sex) {
		this.sex = sex;
	}
	/**
	 * 手机号码
	 * @return
	 */
	@Column(unique=true)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 个人简介
	 * @return
	 */
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	/**
	 * 邮箱
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * qq号码
	 * @return
	 */
	public String getQqnum() {
		return qqnum;
	}
	public void setQqnum(String qqnum) {
		this.qqnum = qqnum;
	}
	/**
	 * qqid
	 * @return
	 */
	public String getQqid() {
		return qqid;
	}
	public void setQqid(String qqid) {
		this.qqid = qqid;
	}
	/**
	 * 微信号码
	 * @return
	 */
	public String getWeixinnum() {
		return weixinnum;
	}
	public void setWeixinnum(String weixinnum) {
		this.weixinnum = weixinnum;
	}
	/**
	 * 微信id
	 * @return
	 */
	public String getWeixinid() {
		return weixinid;
	}
	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}
	/**
	 * 生日
	 * @return
	 */
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	/**
	 * 血型
	 * @return
	 */
	@ManyToOne
	public CategoryItem getBloodType() {
		return bloodType;
	}
	public void setBloodType(CategoryItem bloodType) {
		this.bloodType = bloodType;
	}
	/**
	 * 星座
	 * @return
	 */
	@ManyToOne
	public CategoryItem getConstellation() {
		return constellation;
	}
	public void setConstellation(CategoryItem constellation) {
		this.constellation = constellation;
	}
	/**
	 * 用户头像
	 * @return
	 */
	@OneToOne(cascade=CascadeType.ALL)
	public MediaContent getAvatar() {
		return avatar;
	}
	public void setAvatar(MediaContent avatar) {
		this.avatar = avatar;
	}
	/**
	 * 职业
	 * @return
	 */
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	/**
	 * 地址
	 * @return
	 */
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 经验值
	 * @return
	 */
	public long getExpVal() {
		return expVal;
	}
	public void setExpVal(long expVal) {
		this.expVal = expVal;
	}
	/**
	 * 积分
	 * @return
	 */
	public long getIntegra() {
		return integra;
	}
	public void setIntegra(long integra) {
		this.integra = integra;
	}
	/**
	 * 注册时间
	 * @return
	 */
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	/**
	 * 注册ip
	 * @return
	 */
	public String getRegIp() {
		return regIp;
	}
	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}
	/**
	 * 登录时间
	 * @return
	 */
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	/**
	 * 登录ip
	 * @return
	 */
	public String getLoginip() {
		return loginip;
	}
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}
	/**
	 * 微博id
	 * @return
	 */
	public String getWeiboid() {
		return weiboid;
	}
	public void setWeiboid(String weiboid) {
		this.weiboid = weiboid;
	}
	
	/**
	 * 判断用户信息是否完整
	 * @return
	 */
	@Transient
	public boolean getInfoComplete() {
		if(StringUtils.isBlank(cnname))return false;
		if(StringUtils.isBlank(realName))return false;
		if(StringUtils.isBlank(mobile))return false;
		return true;
	}
}
