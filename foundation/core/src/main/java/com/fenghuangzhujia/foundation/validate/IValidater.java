package com.fenghuangzhujia.foundation.validate;

/**
 * 数据校验器，校验一组数据是否满足指定的校验规则
 * @author pc
 *
 * @param <T>
 */
public interface IValidater<T> {
	
	/**
	 * 校验一组数据是否满足指定的校验规则
	 * @param t 校验值
	 */
	void validate(T t) throws RuntimeException;
}
