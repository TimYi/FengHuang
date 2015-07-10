package com.fenghuangzhujia.eshop.materialManage.material;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.materialManage.product.Product;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContent;

/**
 * 装修材料
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_material")
public class Material extends UUIDBaseModel {

	/**主材所属产品*/
	private Product product;
	/**排序序号*/
	private int ordernum;
	/**展示图片*/
	private MediaContent pic;
	/**对主材的描述信息*/
	private String description;
	
	@ManyToOne(optional=false)
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	@OneToOne
	public MediaContent getPic() {
		return pic;
	}
	public void setPic(MediaContent pic) {
		this.pic = pic;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
