package com.fenghuangzhujia.eshop.view.decorateTechnology.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class DecorateTechnologyDto extends DtoBaseModel {

	/**工艺名称*/
	private String name;
	/**序号*/
	private int ordernum;
	/**详情*/
	private String description;
	/**工艺图*/
	private MediaContentDto pic;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MediaContentDto getPic() {
		return pic;
	}
	public void setPic(MediaContentDto pic) {
		this.pic = pic;
	}
}
