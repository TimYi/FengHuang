package com.fenghuangzhujia.eshop.core.remind;

import java.io.Serializable;

import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.entity.Identified;

/**
 * 此模型的实体，用户那些需要提醒用户读取的实体。
 * 可以获取用户未读的信息数量。
 * @author pc
 *
 */
public interface UnreadRemindModel<ID extends Serializable> extends Identified<ID> {
	
	/**
	 * 和用户关联
	 * @return
	 */
	User getUser();
	
	void setUser(User user);
	
	/**
	 * 是否已读状态
	 * @return
	 */
	boolean getReaded();
	
	void setReaded(boolean readed);
}
