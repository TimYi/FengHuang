package com.fenghuangzhujia.eshop.comment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.comment.dto.CommentDto;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;

@Service
@Transactional
public class CommentService extends DtoSpecificationService<Comment, CommentDto, CommentDto, String> {

}
