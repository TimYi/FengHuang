package com.fenghuangzhujia.foundation.area.dto;

import java.text.MessageFormat;

import com.fenghuangzhujia.foundation.area.Area;
import com.fenghuangzhujia.foundation.area.Area.AreaLevel;

/**
 * 最终展现给用户的最终地点view object
 * @author pc
 *
 */
public class AreaVo {
	public static final String FORMAT="{0} {1} {2} {3}";
	
	private String id;
	private String name;
	private String countryid;
	private String country;	
	private String cityid;
	private String city;	
	private String provid;
	private String prov;
	
	public AreaVo(){}
	
	/**
	 * @param area 地区级地址，否则报 @IllegalArgumentException
	 */
	public AreaVo(Area area) {
		if(!area.getLevel().equals(AreaLevel.AREA)) {
			throw new IllegalArgumentException("输入参数必须是地区级地址");
		}
		id=area.getId();
		name=area.getName();
		Area city=area.getUpperArea();
		if(city==null)return;
		this.city=city.getName();
		cityid=city.getId();
		Area prov=city.getUpperArea();
		if(prov==null)return;
		this.prov=prov.getName();
		this.provid=prov.getId();
		Area country=prov.getUpperArea();
		if(country==null)return;
		this.country=country.getName();
		this.countryid=country.getId();
	}
	
	/**
	 * 地区id
	 * @return
	 */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 地区最终名称
	 * @return
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCountryid() {
		return countryid;
	}
	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}
	/**
	 * 国家名称
	 * @return
	 */
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCityid() {
		return cityid;
	}
	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	/**
	 * 城市名称
	 * @return
	 */
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvid() {
		return provid;
	}
	public void setProvid(String provid) {
		this.provid = provid;
	}
	/**
	 * 省名称
	 * @return
	 */
	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}
	
	public String getFullName() {
		return MessageFormat.format(FORMAT, getCountry(),getCity(),getProv(),getName());
	}
}
