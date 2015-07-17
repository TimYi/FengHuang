package com.fenghuangzhujia.eshop.templateEngine.utils;

public class DefaultTemplateReader implements TemplateReader {
	
	private static final Character startCharacter='{';
	private static final Character endCharacter='}';
	
	/**当前索引，未开始读取时是-1*/
	private int currentIndex=-1;
	/**当前变量开始位置，{{的下一个位置*/
	private int variableStartIndex=0;
	/**当前变量结束位置，}}中第一个}的位置*/
	private int variableEndIndex=0;
	/**当前变量值*/
	private String variable;
	/**模板内容*/
	private String template;
	/**当前标签*/
	private Character[] variableTag=new Character[2];
	/**是否已经开始了一个标签*/
	private boolean tagStarted=false;
	
	private boolean isStartTag() {		
		boolean isStartTag=(startCharacter.equals(variableTag[0]) && startCharacter.equals(variableTag[1]));
		if(isStartTag) {
			if(tagStarted) {
				isStartTag=false;
			} else {
				tagStarted=true;
				variableStartIndex=currentIndex+1;
			}
		}
		return isStartTag;
	}	
	private boolean isEndTag() {
		if(isStartTag())return false;//在判断endTag的同时，将判断startTag的工作也做了。
		boolean isEndTag=(endCharacter.equals(variableTag[0]) && endCharacter.equals(variableTag[1]));
		if(isEndTag) {
			if(!tagStarted) {
				isEndTag=false;
			} else {
				tagStarted=false;
				variableEndIndex=currentIndex-1;
				variable=template.substring(variableStartIndex, variableEndIndex);
			}
		}
		return isEndTag;
	}
	
	/**
	 * 移动到下一个位置，读取此位置上的字符，并记录到variableTag上
	 * @return 是否还有下一个index
	 */
	private boolean moveNext() {
		currentIndex++;
		Character currentCharacter=template.charAt(currentIndex);
		variableTag[0]=variableTag[1];
		variableTag[1]=currentCharacter;
		return currentIndex<(template.length()-1);		
	}
	
	/**
	 * @param template 模板内容
	 */
	public DefaultTemplateReader(String template) {
		this.template=template;
	}

	@Override
	public boolean next() {
		boolean hasNext=moveNext();
		while (hasNext) {
			if(isEndTag()) return true;
			hasNext=moveNext();
		}
		return isEndTag();
	}

	@Override
	public String getVariable() {
		return variable;
	}

	@Override
	public String getVariableName() {
		String name=variable.split(":")[0];
		name=name.trim();
		return name;
	}
	
	@Override
	public String getTemplate() {
		return template;
	}

	@Override
	public void injectCurrentVariable(String value) {
		//注意将{{和}}计算进去
		value=String.valueOf(value);
		int lengthChange=value.length()-(variableEndIndex-variableStartIndex+4);
		String toReplace="{{"+variable+"}}";
		template=template.replace(toReplace, value);
		currentIndex=currentIndex+lengthChange;
	}

	@Override
	public int getVariableStartIndex() {
		return variableStartIndex;
	}

	@Override
	public int getVariableEndIndex() {
		return variableEndIndex;
	}

	@Override
	public int getCurrentIndex() {
		return currentIndex;
	}

}
