package com.fenghuangzhujia.foundation.core.dto;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SimpleDtoAdapter<D,T> implements DtoAdapter<D, T> {

	protected static Logger logger=LoggerFactory.getLogger(SimpleDtoAdapter.class);
	
	@Override
	public List<T> convertDoList(Iterable<? extends D> ds) {
		if(ds==null)return null;
		List<T> ts=new ArrayList<T>();
		for (D d : ds) {
			T t=convert(d);
			ts.add(t);
		}
		return ts;
	}

	@Override
	public List<D> convertDtoList(Iterable<? extends T> ts) {
		if(ts==null)return null;
		List<D> ds=new ArrayList<D>();
		for (T t : ts) {
			D d=convertToDo(t);
			ds.add(d);
		}
		return ds;
	}
	
	/**
	 * 临时解决方案，将单个转换和列表转换用同一个
	 */
	@Override
	public T convertToDto(D d) {
		return convert(d);
	}
}
