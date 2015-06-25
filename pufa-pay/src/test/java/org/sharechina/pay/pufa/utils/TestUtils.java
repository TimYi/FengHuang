package org.sharechina.pay.pufa.utils;

import java.lang.reflect.Field;

public class TestUtils {

	public static void printEachField(Object obj) {
		for (Field field : obj.getClass().getDeclaredFields()) {			
			try {
				field.setAccessible(true);
				Object value=field.get(obj);
				if(value!=null) {
					System.out.print(field.getName()+":");
					System.out.println(value);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
	}
}
