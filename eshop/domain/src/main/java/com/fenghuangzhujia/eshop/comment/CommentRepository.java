package com.fenghuangzhujia.eshop.comment;

import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface CommentRepository extends SpecificationRepository<Comment, String> {

	/**
	 * 根据评论来源id获取评论实体
	 * @param sourceid
	 * @return
	 */
	public Comment getBySourceid(String sourceid);
}
