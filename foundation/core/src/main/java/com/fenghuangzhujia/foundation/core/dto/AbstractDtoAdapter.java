package com.fenghuangzhujia.foundation.core.dto;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.fenghuangzhujia.foundation.mapper.BeanMapper;

public abstract class AbstractDtoAdapter<D, T> extends SimpleDtoAdapter<D, T> {
	
	@Override
	public T convert(D source) {
		if(source==null)return null;
		T t=BeanMapper.map(source, getTClass());
		return postConvert(source, t);
	}
	
	/**
	 * 在dozer转换完一般属性之后，将do对象的特殊属性转换给dto
	 * @param d
	 * @param t
	 * @return
	 */
	public abstract T postConvert(D d, T t);
	
	@Override
	public D convertToDo(T t) {
		if(t==null)return null;
		D d=BeanMapper.map(t, getDClass());
		return postConvertToDo(t, d);
	}
	
	/**
	 * 在dozer转换完一般属性之后，将dto的特殊属性转换给do
	 * @param t
	 * @param d
	 * @return
	 */
	public abstract D postConvertToDo(T t, D d);
	
	@Override
	public D update(T t, D d) {
		if(t==null || d==null)return null;
		BeanMapper.copy(t, d);
		return postUpdate(t, d);
	}
	
	/**
	 * 在dozer转换完一般属性之后，将dto的特殊属性更新给do
	 * @param t
	 * @param d
	 * @return
	 */
	public abstract D postUpdate(T t, D d);
	
	/**
	 * 获取do运行时类型
	 * @return
	 */
	protected Class<D> getDClass() {
		Type t = getClass().getGenericSuperclass();
		Type[] p=((ParameterizedType)t).getActualTypeArguments();
		Class<D> dClass=(Class<D>)p[0];
        return dClass;
	}
	
	/**
	 * 获取dto运行时类型
	 * @return
	 */
	protected Class<T> getTClass() {
		Type t = getClass().getGenericSuperclass();
		Type[] p=((ParameterizedType)t).getActualTypeArguments();
		Class<T> tClass=(Class<T>)p[1];
        return tClass;
	}
}
