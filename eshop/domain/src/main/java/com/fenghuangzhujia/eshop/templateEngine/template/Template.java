package com.fenghuangzhujia.eshop.templateEngine.template;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContent;

@Entity
@Table(name="fhzj_template")
public class Template extends UUIDBaseModel {

	/**模板名称*/
	private String name;
	/**模板分类，是一个标记字段*/
	private String type;
	/**模板效果图*/
	private MediaContent pic;
	/**解析之后的模板动态字段*/
	private Set<VariableDefinition> definitions;
	/**模板内容，富文本字段*/
	private String content;
	
	@Column(unique=true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@OneToOne(cascade=CascadeType.ALL)
	public MediaContent getPic() {
		return pic;
	}
	public void setPic(MediaContent pic) {
		this.pic = pic;
	}
	@OneToMany(mappedBy="template",cascade=CascadeType.ALL)
	public Set<VariableDefinition> getDefinitions() {
		return definitions;
	}
	public void setDefinitions(Set<VariableDefinition> definitions) {
		this.definitions = definitions;
	}
	@Type(type="text")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
