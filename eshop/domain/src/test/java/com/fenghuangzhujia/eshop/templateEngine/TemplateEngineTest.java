package com.fenghuangzhujia.eshop.templateEngine;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.fenghuangzhujia.eshop.templateEngine.fragment.VariableValue;
import com.fenghuangzhujia.eshop.templateEngine.template.VariableDefinition;
import com.fenghuangzhujia.eshop.templateEngine.template.VariableDefinition.FieldType;
import com.fenghuangzhujia.eshop.templateEngine.utils.TemplateResolver;

public class TemplateEngineTest {

	//@Test
	public void resolve() {
		String template="<div>arguments:{{ test:TEXT :测试数据 }}</div>";
		resolveTemplate(template);
	}
	
	@Test
	public void calculate() {
		String template="<div>arguments:{{ test:TEXT :测试数据 }}; argument2:{{ test2 : TEXT: 测试}}</div>";
		VariableValue value=new VariableValue();
		value.setName("test");
		value.setType(FieldType.TEXT);
		value.setText("testValue");
		VariableValue value2=new VariableValue();
		value2.setName("test2");
		value2.setType(FieldType.TEXT);
		value2.setText("testValue2");
		Set<VariableValue> values=new HashSet<>();
		values.add(value);
		values.add(value2);
		String result=TemplateResolver.calculateTemplate(template, values);
		System.out.println(result);
	}
	
	private void resolveTemplate(String template) {
		Set<VariableDefinition> definitions=TemplateResolver.fromTemplate(template);
		for (VariableDefinition definition : definitions) {
			System.out.println("name:"+definition.getName()+"\t");
			System.out.println("type:"+definition.getType()+"\t");
			System.out.println("description:"+definition.getDescription()+"\n");
		}
	}
}
