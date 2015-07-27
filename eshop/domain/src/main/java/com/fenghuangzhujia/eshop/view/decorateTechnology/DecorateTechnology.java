package com.fenghuangzhujia.eshop.view.decorateTechnology;

import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Entity;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContent;

@Entity
@Table(name="fhzj_decorate_technology")
public class DecorateTechnology extends UUIDBaseModel {

	/**工艺名称*/
	private String name;
	/**序号*/
	private int ordernum;
	/**详情*/
	private String description;
	/**工艺图*/
	private MediaContent pic;
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
	@OneToOne
	public MediaContent getPic() {
		return pic;
	}
	public void setPic(MediaContent pic) {
		this.pic = pic;
	}
}
