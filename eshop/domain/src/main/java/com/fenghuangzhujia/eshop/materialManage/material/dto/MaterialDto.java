package com.fenghuangzhujia.eshop.materialManage.material.dto;

import java.util.Set;

import com.fenghuangzhujia.eshop.materialManage.product.dto.SimpleProductDto;
import com.fenghuangzhujia.eshop.prudoct.packages.dto.DecoratePackageVo;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class MaterialDto extends DtoBaseModel {

	/**排序序号*/
	private int ordernum;
	/**展示图片*/
	private MediaContentDto pic;
	/**对主材的描述信息*/
	private String description;
	/**所属产品*/
	private SimpleProductDto product;
	/**类型，如家具，灯饰等*/
	private String type;
	/**使用此材料的套餐*/
	private Set<DecoratePackageVo> packages;
	
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public MediaContentDto getPic() {
		return pic;
	}
	public void setPic(MediaContentDto pic) {
		this.pic = pic;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public SimpleProductDto getProduct() {
		return product;
	}
	public void setProduct(SimpleProductDto product) {
		this.product = product;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Set<DecoratePackageVo> getPackages() {
		return packages;
	}
	public void setPackages(Set<DecoratePackageVo> packages) {
		this.packages = packages;
	}
}
