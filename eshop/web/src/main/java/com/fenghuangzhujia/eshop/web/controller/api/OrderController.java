package com.fenghuangzhujia.eshop.web.controller.api;

import java.util.Set;

import org.sharechina.pay.pufa.protocal.AccountType;
import org.sharechina.pay.pufa.protocal.PayBank;
import org.sharechina.pay.pufa.protocal.RequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrder.OrderStatus;
import com.fenghuangzhujia.eshop.core.commerce.order.dto.GoodOrderDto;
import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrderService;
import com.fenghuangzhujia.eshop.core.commerce.pay.PufaPayService;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class OrderController {
	
	@Autowired
	private GoodOrderService orderService;
	@Autowired
	private PufaPayService pufaPayService;

	@RequestMapping(value="user/orders",method=RequestMethod.GET)
	public String list(@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="8") Integer size, OrderStatus status) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		PagedList<GoodOrderDto> orders=orderService.findByUser(userid, page, size, status);
		return RequestResult.success(orders).toJson();
	}
	
	@RequestMapping(value="user/order/{id}",method=RequestMethod.GET)
	public String getOne(@PathVariable String id) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		GoodOrderDto result=orderService.findOneByUser(userid, id);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="order/{orderId}/pay/pufa",method=RequestMethod.POST)
	public String pufaPay(@PathVariable String orderId, Set<String> couponsIds, 
			PayBank payBank, AccountType accountType){
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userId=details.getId();
		RequestModel result=
				pufaPayService.calculatePayArgs(userId, orderId, couponsIds, payBank, accountType);
		return RequestResult.success(result).toJson();
	}
}
