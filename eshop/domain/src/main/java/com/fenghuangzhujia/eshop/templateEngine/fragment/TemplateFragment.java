package com.fenghuangzhujia.eshop.templateEngine.fragment;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.fenghuangzhujia.eshop.templateEngine.template.Template;
import com.fenghuangzhujia.eshop.templateEngine.utils.TemplateResolver;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
@Table(name="fhzj_template_fragment")
public class TemplateFragment extends UUIDBaseModel {

	/**对应的模板*/
	private Template template;
	/**实际的变量*/
	private Set<VariableValue> values;
	/**计算生成的模板*/
	private String templateValue;
	/**计算生成模板的时间*/
	private Date cachedTime;
	
	@ManyToOne(optional=false)
	public Template getTemplate() {
		return template;
	}
	public void setTemplate(Template template) {
		this.template = template;
	}
	
	@OneToMany(mappedBy="fragment",cascade=CascadeType.ALL)
	public Set<VariableValue> getValues() {
		return values;
	}
	public void setValues(Set<VariableValue> values) {
		this.values = values;
	}
	public String getTemplateValue() {
		return templateValue;
	}
	public void setTemplateValue(String templateValue) {
		this.templateValue = templateValue;
	}
	public Date getCachedTime() {
		return cachedTime;
	}
	public void setCachedTime(Date cachedTime) {
		this.cachedTime = cachedTime;
	}
	
	@Transient
	public String getTemplateContent() {
		if(shouldReCalculate()) {
			calculateTemplateValue();
		}
		return templateValue;
	}
	
	@Transient
	/**计算模板值*/
	public void calculateTemplateValue() {
		this.templateValue=TemplateResolver.calculateTemplate(template.getContent(), values);
		this.cachedTime=new Date();
	}
	
	@Transient
	/**缓存的模板是否已经过期*/
	public boolean shouldReCalculate() {
		if(StringUtils.isBlank(templateValue)) return true;
		//发现template在缓存之后有变动
		if(cachedTime==null) {
			return true;
		}
		//如果模板定义改变，需要重新计算
		if(cachedTime.before(template.getUpdateTime())) {
			return true;
		}
		return false;
	}
}
