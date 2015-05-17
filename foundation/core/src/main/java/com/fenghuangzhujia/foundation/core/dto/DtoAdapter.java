package com.fenghuangzhujia.foundation.core.dto;

import java.util.Collection;

import org.springframework.core.convert.converter.Converter;

/**
 * 为dto和do之间的转换提供互相转换方法。
 * 继承 @Converter 接口，方便使用 @Page 的map方法 
 * @author ytm
 *
 * @param <D>
 * @param <T>
 */
public interface DtoAdapter<D, T> extends Converter<D, T> {
	
	/**
	 * 将Dto转为Do
	 * @param t never null，应返回空值
	 * @return
	 */
	D convertToDo(T t);
	
	/**
	 * 更新Do
	 * @param t never null
	 * @param d never null
	 * @return
	 */
	D update(T t, D d);
	
	Collection<T> convertDoList(Iterable<? extends D> ds);
	
	Collection<D> convertToList(Iterable<? extends T> ts);
}
