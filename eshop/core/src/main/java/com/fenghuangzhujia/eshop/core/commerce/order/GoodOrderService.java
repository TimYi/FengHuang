package com.fenghuangzhujia.eshop.core.commerce.order;

import static com.fenghuangzhujia.eshop.core.base.SystemErrorCodes.ILLEGAL_ARGUMENT;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fenghuangzhujia.eshop.core.commerce.goods.Good;
import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrder.OrderStatus;
import com.fenghuangzhujia.eshop.core.commerce.order.dto.GoodOrderDto;
import com.fenghuangzhujia.eshop.core.commerce.order.dto.GoodOrderDtoConverter;
import com.fenghuangzhujia.eshop.core.commerce.pay.OrderPay;
import com.fenghuangzhujia.eshop.core.commerce.pay.OrderPayRepository;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

@Service
@Transactional
public class GoodOrderService {
	
	@Autowired
	private GoodOrderDtoConverter converter;
	@Autowired
	private GoodOrderRepository repository;
	@Autowired
	private OrderPayRepository orderPayRepository;
	
	/** 生成未支付订单 */
	public GoodOrder createOrderToPay(User user, Good good, double price, int count, String mobile, String realName) {
		GoodOrder order=new GoodOrder();
		order.setUser(user);
		order.setGood(good);
		order.setPrice(price);
		order.setCount(count);
		order.setMobile(mobile);
		order.setRealName(realName);
		order=repository.save(order);
		
		OrderPay pay=new OrderPay();
		pay.setOrder(order);
		pay.setTotalMoney(order.getPrice());
		orderPayRepository.save(pay);
		
		return order;
	}
	
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
	
	public PagedList<GoodOrderDto> findByStatus(int page, int size, OrderStatus status) {
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
}
