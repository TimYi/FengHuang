package com.fenghuangzhujia.eshop.web.controller.api;

import org.sharechina.pay.pufa.protocal.AccountType;
import org.sharechina.pay.pufa.protocal.PayBank;
import org.sharechina.pay.pufa.protocal.RequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fenghuangzhujia.eshop.appoint.AppointService;
import com.fenghuangzhujia.eshop.commerce.drawback.DrawbackService;
import com.fenghuangzhujia.eshop.commerce.order.GoodOrderService;
import com.fenghuangzhujia.eshop.commerce.order.GoodOrder.OrderStatus;
import com.fenghuangzhujia.eshop.commerce.order.dto.GoodOrderDto;
import com.fenghuangzhujia.eshop.commerce.pay.PufaPay;
import com.fenghuangzhujia.eshop.commerce.pay.PufaPayService;
import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.eshop.core.utils.LogUtils;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class OrderController {
	
	@Autowired
	private GoodOrderService orderService;
	@Autowired
	private PufaPayService pufaPayService;
	@Autowired
	private AppointService appointService;
	@Autowired
	private DrawbackService drawbackService;

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
	
	@RequestMapping(value="user/order/{id}/cancel",method=RequestMethod.POST)
	public String cancelOrder(@PathVariable String id) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		orderService.calcelOrder(id, userid);
		return RequestResult.success("取消成功").toJson();
	}
	
	@RequestMapping(value="order/{orderId}/pay/pufa",method=RequestMethod.POST)
	public String pufaPay(@PathVariable String orderId, String couponsId, 
			PayBank payBank, AccountType accountType){
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userId=details.getId();
		RequestModel result=
				pufaPayService.calculatePayArgs(userId, orderId, couponsId, payBank, accountType);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="user/order/{orderId}/drawback",method=RequestMethod.POST)
	public String drawback(@PathVariable String orderId, String reason) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userId=details.getId();
		drawbackService.drawback(orderId, userId, reason);
		return RequestResult.success("已申请退款，客服将在3-5个工作日内与您联系，请耐心等待。").toJson();
	}
	
	@RequestMapping(value="pufa/revoke",method=RequestMethod.POST)
	public ModelAndView pufaRevoke(String Plain, String Signature) {
		//LogUtils.errorLog(Plain);//先记录下返回的数据，查看是哪里出现异常。
		ModelAndView view=new ModelAndView("redirect:http://IFHZJ.com/payback.html");
		try {
			PufaPay pufaPay=pufaPayService.revoke(Plain,Signature);
			view.addObject("result", true);
			String orderId=pufaPayService.findOrderByPufaPay(pufaPay.getId());
			view.addObject("orderId", orderId);
			try {
				//支付订单后自动预约体验馆
				appointService.appointAfterPay(orderId);
			} catch (Exception e) {
				LogUtils.errorLog(e);
				// 支付回调确认成功之后，无论如何返回给用户支付成功的页面
			}			
		} catch (Exception e) {
			LogUtils.errorLog(e);
			view.addObject("result", false);
		}
		return view;
	}
}
