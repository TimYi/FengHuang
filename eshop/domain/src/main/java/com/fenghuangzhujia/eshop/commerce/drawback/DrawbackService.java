package com.fenghuangzhujia.eshop.commerce.drawback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.commerce.drawback.Drawback.DrawbackStatus;
import com.fenghuangzhujia.eshop.commerce.order.GoodOrder;
import com.fenghuangzhujia.eshop.commerce.order.GoodOrderRepository;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.persistance.PageableBuilder;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.mapper.BeanMapper;

@Service
@Transactional
public class DrawbackService {

	@Autowired
	private DrawbackManager drawbackManager;
	@Autowired
	private DrawbackRepository drawbackRepository;
	@Autowired
	private GoodOrderRepository goodOrderRepository;
	@Autowired
	private UserRepository userRepository;
	
	public PagedList<DrawbackDto> findByStatus(DrawbackStatus status, int page, int size) {
		Pageable pageable=PageableBuilder.build(page, size);
		Page<Drawback> drawbacks;
		if(status==null) {
			drawbacks=drawbackRepository.findAll(pageable);
		} else {
			drawbacks=drawbackRepository.findByStatus(status, pageable);
		}
		Page<DrawbackDto> result=drawbacks.map(getConverter());
		return new PagedList<>(result);
	}
	
	public DrawbackDto findOne(String id) {
		Drawback drawback=drawbackRepository.findOne(id);
		return getConverter().convert(drawback);
	}
	
	public void drawback(String orderId, String userId, String reason) {
		GoodOrder order=goodOrderRepository.findOne(orderId);
		if(order==null)return;
		if(!order.getUser().getId().equals(userId))
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "这不是您的订单");
		drawbackManager.drawback(order, reason);
	}
	
	public void approve(String drawbackId, String userId) {
		Drawback drawback=drawbackRepository.findOne(drawbackId);
		User authenticater=userRepository.findOne(userId);
		drawbackManager.approve(drawback, authenticater);
	}
	
	public void disapprove(String drawbackId, String userId, String reason) {
		Drawback drawback=drawbackRepository.findOne(drawbackId);
		User authenticater=userRepository.findOne(userId);
		drawbackManager.disapprove(drawback, authenticater, reason);
	}
	
	private Converter<Drawback, DrawbackDto> getConverter() {
		return new Converter<Drawback, DrawbackDto>() {
			@Override
			public DrawbackDto convert(Drawback source) {
				if(source==null)return null;
				DrawbackDto dto=BeanMapper.map(source, DrawbackDto.class);
				return dto;
			}
		};
	}
}
