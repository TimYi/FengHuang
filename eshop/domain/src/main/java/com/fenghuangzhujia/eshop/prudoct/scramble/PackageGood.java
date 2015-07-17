package com.fenghuangzhujia.eshop.prudoct.scramble;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fenghuangzhujia.eshop.cases.DecorateCase;
import com.fenghuangzhujia.eshop.commerce.goods.Good;
import com.fenghuangzhujia.eshop.core.area.Area;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage;
import com.fenghuangzhujia.foundation.media.MediaContent;

/**
 * 套餐商品
 * 抢购一个套餐，生成一个套餐商品
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_decorat_good")
@DiscriminatorValue(value="DECORATE_PACKAGE")
public class PackageGood extends Good {
	
	/**选购的套餐*/
	private DecoratePackage decoratePackage;
	/**选择的装修案例*/
	private DecorateCase decorateCase;
	/**预约时选定城市*/
	private Area city;
	
	@ManyToOne
	public DecoratePackage getDecoratePackage() {
		return decoratePackage;
	}
	public void setDecoratePackage(DecoratePackage decoratePackage) {
		this.decoratePackage = decoratePackage;
	}
	@ManyToOne
	public DecorateCase getDecorateCase() {
		return decorateCase;
	}	
	public void setDecorateCase(DecorateCase decorateCase) {
		this.decorateCase = decorateCase;
	}
	@OneToOne
	public Area getCity() {
		return city;
	}
	public void setCity(Area city) {
		this.city = city;
	}
	
	@Transient
	public MediaContent getMainPic() {
		if(decoratePackage==null)return null;
		return getDecoratePackage().getMainPic();
	}
}
