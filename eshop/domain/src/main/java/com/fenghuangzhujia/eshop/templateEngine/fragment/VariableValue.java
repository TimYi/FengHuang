package com.fenghuangzhujia.eshop.templateEngine.fragment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.templateEngine.template.VariableDefinition.FieldType;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContent;

@Entity
@Table(name="fhzj_variable_value")
public class VariableValue extends UUIDBaseModel {

	/**变量名称*/
	private String name;
	/**变量类型*/
	private FieldType type;
	/**TEXT型变量值*/
	private String text;
	/**IMG型变量值*/
	private MediaContent pic;
	/**所属fragment*/
	private TemplateFragment fragment;
	
	@Column(nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(nullable=false)
	public FieldType getType() {
		return type;
	}
	public void setType(FieldType type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@OneToOne
	public MediaContent getPic() {
		return pic;
	}
	public void setPic(MediaContent pic) {
		this.pic = pic;
	}
	@ManyToOne(optional=false)
	public TemplateFragment getFragment() {
		return fragment;
	}
	public void setFragment(TemplateFragment fragment) {
		this.fragment = fragment;
	}
	
	/**
	 * 转换为注入模板的值
	 */
	@Override
	public String toString() {
		if(type==FieldType.TEXT) {
			if(text==null)return "";
			return text;
		} else if(type==FieldType.IMG) {
			if(pic==null) return "#";
			return pic.getUrl();
		} else {//URL类型
			if(text==null) return "#";
			return text;
		}
	}
}
