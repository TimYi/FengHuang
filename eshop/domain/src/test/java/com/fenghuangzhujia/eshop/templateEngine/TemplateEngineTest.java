package com.fenghuangzhujia.eshop.templateEngine;

import java.util.Set;

import org.junit.Test;

import com.fenghuangzhujia.eshop.templateEngine.template.TemplateResolver;
import com.fenghuangzhujia.eshop.templateEngine.template.VariableDefinition;

public class TemplateEngineTest {

	@Test
	public void resolve() {
		String template="<div>arguments{{ test:TEXT :测试数据 }}</div>";
		resolveTemplate(template);
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
