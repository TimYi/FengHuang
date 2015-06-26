package org.sharechina.pay.pufa.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Consumer;

import org.apache.commons.collections.KeyValue;
import org.apache.commons.collections.keyvalue.DefaultKeyValue;

public class ReflectUtil {

	public static void foreachField(Object obj, Consumer<KeyValue> func) {
		Field[] fields=obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			String name=field.getName();
			Object value=getValueFromGetter(obj, field);
			KeyValue pair=new DefaultKeyValue(name, value);
			func.accept(pair);
		}
	}
	
	public static Object getValueFromGetter(Object obj, Field field) {
		field.setAccessible(true);
		String name=field.getName();
		String getterName=StringUtils.firstCharToUpper(name);
		getterName="get"+getterName;
		Object value;
		try {
			Method getter=obj.getClass().getDeclaredMethod(getterName);
			value=getter.invoke(obj);
		} catch (Exception e) {
			try {
				value=field.get(obj);
			} catch (Exception e1) {
				throw new RuntimeException(e1);
			} 
		}
		return value;
	}
}
