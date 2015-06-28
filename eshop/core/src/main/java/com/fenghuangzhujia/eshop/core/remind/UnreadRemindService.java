package com.fenghuangzhujia.eshop.core.remind;

import java.io.Serializable;

public interface UnreadRemindService<ID extends Serializable> {

	Long countByIsReaded(String userid, boolean readed);
	
	void setIsReaded(ID id, boolean readed);
}
