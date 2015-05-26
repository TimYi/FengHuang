package com.fenghuangzhujia.foundation.core.entity;

/**
 * 用于逻辑删除的模型，应该有一个字段表示是否被删除
 * @author pc
 *
 */
public interface LogicDeleteModel {
	boolean isDeleted();
	void setDeleted(boolean deleted);
}
