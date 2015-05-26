package com.fenghuangzhujia.eshop.core.schedual;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fenghuangzhujia.eshop.core.schedual.activity.Activity;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

/**
 * 时间表，记录人员全部活动信息
 * 时间表本身不能存在时间冲突，没有其它限制
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_schedual")
public class Schedual extends UUIDBaseModel {
	
	
	private Set<Activity> actives;

	/**
	 * @return the actives
	 */
	@OneToMany(mappedBy="schedual",cascade=CascadeType.ALL)
	public Set<Activity> getActives() {
		return actives;
	}

	/**
	 * @param actives the actives to set
	 */
	public void setActives(Set<Activity> actives) {
		this.actives = actives;
	}
	
	@Transient
	public Activity getActive(String id) {
		if(id==null || this.actives==null)return null;
		for (Activity active : actives) {
			if(id.equals(active.getId())) {
				return active;
			}
		}
		return null;
	}
}
