package com.fenghuangzhujia.eshop.templateEngine.utils;

/**
 * 模板变量读取器
 * 变量放在{{}}中
 * @author pc
 *
 */
public interface TemplateReader {

	/**
	 * 读取下一个变量定义
	 * @return 返回是否找到
	 */
	boolean next();
	
	/**
	 * next返回true后，通过此函数返回变量值
	 * @return
	 */
	String getVariable();
	
	/**
	 * 获取当前变量名称
	 * @return
	 */
	String getVariableName();
	
	/**
	 * 获取当前模板值
	 * @return
	 */
	String getTemplate();
	
	/**
	 * 将变量值注入当前变量
	 * @param value
	 */
	void injectCurrentVariable(String value);
	
	
	/**
	 * 变量开始位置，从0算起，指示的是{{的后一个位置
	 * @return
	 */
	int getVariableStartIndex();
	
	/**
	 * 变量结束位置，从0算起，指示的是}}的第一个}的位置
	 * @return
	 */
	int getVariableEndIndex();
	
	/**
	 * 游标当前位置
	 * @return
	 */
	int getCurrentIndex();
}
