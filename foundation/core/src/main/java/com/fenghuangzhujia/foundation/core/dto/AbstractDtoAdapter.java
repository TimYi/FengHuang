package com.fenghuangzhujia.foundation.core.dto;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fenghuangzhujia.foundation.mapper.BeanMapper;

public abstract class AbstractDtoAdapter<D, T> implements DtoAdapter<D, T> {
	
	protected static Logger logger=LoggerFactory.getLogger(AbstractDtoAdapter.class);

	@Override
	public Collection<T> convertDoList(Iterable<? extends D> ds) {
		if(ds==null)return null;
		List<T> ts=new ArrayList<T>();
		for (D d : ds) {
			T t=convert(d);
			ts.add(t);
		}
		return ts;
	}

	@Override
	public Collection<D> convertToList(Iterable<? extends T> ts) {
		if(ts==null)return null;
		List<D> ds=new ArrayList<D>();
		for (T t : ts) {
			D d=convertToDo(t);
			ds.add(d);
		}
		return ds;
	}
	
	@Override
	public final T convert(D source) {
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
	public final D convertToDo(T t) {
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
	public final D update(T t, D d) {
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
