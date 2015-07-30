package com.fenghuangzhujia.eshop.materialManage.brand.dto;

import java.util.List;

import com.fenghuangzhujia.eshop.materialManage.product.dto.ProductDto;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

/**
 * 会级联从品牌到素材的全部数据
 * @author pc
 *
 */
public class BrandVo extends DtoBaseModel {

	/**品牌名称*/
	private String name;
	/**排序序号*/
	private int ordernum;
	/**品牌logo*/
	private MediaContentDto logo;
	/**品牌旗下产品*/
	private List<ProductDto> products;
	
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
	public List<ProductDto> getProducts() {
		return products;
	}
	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}
}
