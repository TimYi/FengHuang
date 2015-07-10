package com.fenghuangzhujia.eshop.materialManage.brand.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

/**
 * 为下级对象提供简单的级联对象
 * @author pc
 *
 */
public class SimpleBrandDto extends DtoBaseModel {

	/**品牌名称*/
	private String name;
	/**品牌logo*/
	private MediaContentDto logo;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MediaContentDto getLogo() {
		return logo;
	}
	public void setLogo(MediaContentDto logo) {
		this.logo = logo;
	}
}
