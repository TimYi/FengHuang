package com.fenghuangzhujia.foundation.area;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
@Table(name="fhzj_area")
public class Area extends UUIDBaseModel {
	
	private Area upperArea;
	private Set<Area> underAreas;
	private String name;
	private AreaLevel level;
	
	/**
	 * 区域等级
	 * @return
	 */
	public AreaLevel getLevel() {
		return level;
	}
	public void setLevel(AreaLevel level) {
		this.level = level;
	}
	
	/**
	 * 上级区域
	 * @return
	 */
	@ManyToOne
	public Area getUpperArea() {
		return upperArea;
	}
	public void setUpperArea(Area upperArea) {
		this.upperArea = upperArea;
	}
	
	/**
	 * 下辖区域
	 * @return
	 */
	@OneToMany(mappedBy="upperArea")
	public Set<Area> getUnderAreas() {
		return underAreas;
	}
	public void setUnderAreas(Set<Area> underAreas) {
		this.underAreas = underAreas;
	}
	
	/**
	 * 区域名称
	 * @return
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}




	/**
	 * 区域等级
	 * @author pc
	 *
	 */
	public static enum AreaLevel {
		/**
		 * 国家
		 */
		COUNTRY,
		/**
		 * 省
		 */
		PROV,
		/**
		 * 市
		 */
		CITY,
		/**
		 * 区域
		 */
		AREA;
	}
}
