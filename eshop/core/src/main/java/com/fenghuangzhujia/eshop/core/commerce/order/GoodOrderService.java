package com.fenghuangzhujia.eshop.core.commerce.order;

import static com.fenghuangzhujia.eshop.core.base.SystemErrorCodes.ILLEGAL_ARGUMENT;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrder.OrderStatus;
import com.fenghuangzhujia.eshop.core.commerce.order.dto.GoodOrderDto;
import com.fenghuangzhujia.eshop.core.commerce.order.dto.GoodOrderDtoConverter;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

@Service
@Transactional
public class GoodOrderService {
	
	@Autowired
	private GoodOrderDtoConverter converter;
	@Autowired
	private GoodOrderRepository repository;
	
	public PagedList<GoodOrderDto> findByUser(String userid, int page, int size, OrderStatus status) {
		PageRequest pageable=new PageRequest(page-1, size);
		Page<GoodOrder> goods;
		if(status==null) {
			goods=repository.findByUserId(userid, pageable);
		} else {
			goods=repository.findByUserIdAndStatus(userid, status, pageable);
		}
		Page<GoodOrderDto> result=goods.map(converter);
		return new PagedList<>(result);
	}
	
	public PagedList<GoodOrderDto> findPage(int page, int size, OrderStatus status) {
		PageRequest pageable=new PageRequest(page-1, size);
		Page<GoodOrder> goods;
		if(status==null) {
			goods=repository.findAll(pageable);
		} else {
			goods=repository.findByStatus(status, pageable);
		}
		Page<GoodOrderDto> result=goods.map(converter);
		return new PagedList<>(result);
	}
	
	public GoodOrderDto findOneByUser(String userid, String id) {
		GoodOrder order=repository.findOne(id);
		if(!order.getUser().getId().equals(userid))throw new ErrorCodeException(ILLEGAL_ARGUMENT,"这不是您的订单");
		return converter.convert(order);
	}
	
	/**
	 * 改变订单状态，完成订单
	 * @param id
	 */
	public void complete(String id) {
		GoodOrder order=repository.findOne(id);
		if(order==null)throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT);
		order.setStatus(OrderStatus.COMPLETE);
		repository.save(order);
	}
}
