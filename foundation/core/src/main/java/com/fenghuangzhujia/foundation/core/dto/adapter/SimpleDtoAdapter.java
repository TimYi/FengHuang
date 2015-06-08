package com.fenghuangzhujia.foundation.core.dto.adapter;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SimpleDtoAdapter<D,T, I> implements DtoAdapter<D, T, I> {

	protected static Logger logger=LoggerFactory.getLogger(SimpleDtoAdapter.class);
	
	@Override
	public List<T> convertDoList(Iterable<? extends D> ds) {
		if(ds==null)return null;
		List<T> ts=new ArrayList<T>();
		for (D d : ds) {
			T t=convertToDto(d);
			ts.add(t);
		}
		return ts;
	}
	
	/**
	 * 临时解决方案，将单个转换和列表转换用同一个
	 * 此方法用于列表数据的单个转换
	 */
	@Override
	public T convertToDto(D d) {
		return convert(d);
	}
}
