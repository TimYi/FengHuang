package org.sharechina.pay.pufa.protocal;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.sharechina.pay.pufa.common.ReflectUtil;

/**
 * 可以转换为浦发plain字段格式的数据
 * 通常用于协议参数
 * plain格式：param1=value1|param2=value2
 * @author pc
 *
 */
public interface PlainData {

	public static final DozerBeanMapper dozer=new DozerBeanMapper();
		
	default String toPlain() {
		return toPlain(this);
	}	
	
	/**
	 * 转换为'param1=value1|param2=value2'的字符串，不要出现空值
	 * @param pojo
	 * @return
	 */
	public static String toPlain(Object pojo) {	
		StringBuilder sb=new StringBuilder();
		ReflectUtil.foreachField(pojo, (pair)-> {
			Object name=pair.getKey();
			Object value=pair.getValue();
			if(value==null)return;
			sb.append(name);
			sb.append("=");
			sb.append(value);
			sb.append("|");
		});
		String result=sb.toString();
		result=StringUtils.removeEnd(result, "|");
		return result;
	}
	
	public static <T> T fromPlain(Class<T> tClass, String plain) {
		if(plain==null)return null;
		String[] keyvalues=StringUtils.split(plain, '|');
		Map<String, Object> params=new HashMap<String, Object>();
		for (String keyvalue : keyvalues) {
			String[] pair=StringUtils.split(keyvalue,"=");
			String name=pair[0];
			name=org.sharechina.pay.pufa.common.StringUtils.firstCharToLower(name);
			params.put(name, pair[1]);
		}
		T t=dozer.map(params, tClass);
		return t;
	}
}
