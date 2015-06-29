package com.fenghuangzhujia.eshop.core.remind;

import java.io.Serializable;

public interface UnreadRemindRepository<T extends UnreadRemindModel<ID>, ID extends Serializable> {

	/**
	 * 获取用户已读或者未读消息数量
	 * @param userid
	 * @param readed
	 * @return
	 */
	Long countByUserIdAndReaded(String userid, boolean readed);
}
