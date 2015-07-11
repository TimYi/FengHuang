package com.fenghuangzhujia.eshop.materialManage.material;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.materialManage.product.Product;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage;
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
	/**对应家具，灯具，家具饰品等品类*/
	private MaterialType type;
	/**使用此材料的套餐*/
	private Set<DecoratePackage> packages;
	
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
	@OneToOne
	public MaterialType getType() {
		return type;
	}
	public void setType(MaterialType type) {
		this.type = type;
	}
	@ManyToMany
	@JoinTable(name="fhzj_material_packages")
	public Set<DecoratePackage> getPackages() {
		return packages;
	}
	public void setPackages(Set<DecoratePackage> packages) {
		this.packages = packages;
	}
}
