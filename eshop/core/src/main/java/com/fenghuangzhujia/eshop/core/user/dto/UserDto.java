package com.fenghuangzhujia.eshop.core.user.dto;

import java.util.Date;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.core.enums.BloodType;
import com.fenghuangzhujia.foundation.core.enums.CardType;
import com.fenghuangzhujia.foundation.core.enums.Constellation;
import com.fenghuangzhujia.foundation.core.enums.Sex;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class UserDto extends DtoBaseModel {
	private String username;	
	private Boolean verified;
	private String cnname;
	private String ename;
	private MediaContentDto avatar;
	private Sex sex;
	private Date birthDay;
	private Constellation astro;
	private BloodType bloodType;
	private String trade;
	private String liveProv;
	private String liveCity;
	private String homeCountry;
	private CardType cardType;
	private String cardNum;
	private String intro;
	private String email;
	private String qqnum;
	private String mobile;
	private String telephone;
	private String addressProv;
	private String addressCity;
	private String addressCountry;
	private String address;
	private String zipcode;
	private Long expVal;
	private Long integra;
	private String qqid;
	private String weiboid;
	private String weixinid;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Boolean getVerified() {
		return verified;
	}
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String cnname) {
		this.cnname = cnname;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public MediaContentDto getAvatar() {
		return avatar;
	}
	public void setAvatar(MediaContentDto avatar) {
		this.avatar = avatar;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public Constellation getAstro() {
		return astro;
	}
	public void setAstro(Constellation astro) {
		this.astro = astro;
	}
	public BloodType getBloodType() {
		return bloodType;
	}
	public void setBloodType(BloodType bloodType) {
		this.bloodType = bloodType;
	}
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	public String getLiveProv() {
		return liveProv;
	}
	public void setLiveProv(String liveProv) {
		this.liveProv = liveProv;
	}
	public String getLiveCity() {
		return liveCity;
	}
	public void setLiveCity(String liveCity) {
		this.liveCity = liveCity;
	}
	public String getHomeCountry() {
		return homeCountry;
	}
	public void setHomeCountry(String homeCountry) {
		this.homeCountry = homeCountry;
	}
	public CardType getCardType() {
		return cardType;
	}
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddressProv() {
		return addressProv;
	}
	public void setAddressProv(String addressProv) {
		this.addressProv = addressProv;
	}
	public String getAddressCity() {
		return addressCity;
	}
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}
	public String getAddressCountry() {
		return addressCountry;
	}
	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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
	public String getQqid() {
		return qqid;
	}
	public void setQqid(String qqid) {
		this.qqid = qqid;
	}
	public String getWeiboid() {
		return weiboid;
	}
	public void setWeiboid(String weiboid) {
		this.weiboid = weiboid;
	}
	public String getWeixinid() {
		return weixinid;
	}
	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}
}
