package com.fenghuangzhujia.eshop.meteriaManage.brand.dto;

import java.util.Set;

import com.fenghuangzhujia.eshop.meteriaManage.product.dto.ProductVo;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

/**
 * 后台使用数据，数据级联较少
 * @author pc
 *
 */
public class BrandDto extends DtoBaseModel {

	/**品牌名称*/
	private String name;
	/**排序序号*/
	private int ordernum;
	/**品牌logo*/
	private MediaContentDto logo;
	/**品牌旗下产品*/
	private Set<ProductVo> products;
	
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
	public MediaContentDto getLogo() {
		return logo;
	}
	public void setLogo(MediaContentDto logo) {
		this.logo = logo;
	}
	public Set<ProductVo> getProducts() {
		return products;
	}
	public void setProducts(Set<ProductVo> products) {
		this.products = products;
	}
}
