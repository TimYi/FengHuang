package com.fenghuangzhujia.eshop.commerce.drawback;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenghuangzhujia.eshop.commerce.drawback.Drawback.DrawbackStatus;
import com.fenghuangzhujia.eshop.commerce.order.GoodOrder;
import com.fenghuangzhujia.eshop.commerce.order.GoodOrder.OrderStatus;
import com.fenghuangzhujia.eshop.commerce.pay.OrderPay;
import com.fenghuangzhujia.eshop.commerce.pay.PufaPay;
import com.fenghuangzhujia.eshop.commerce.pay.PufaPayService;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

@Service
@Transactional
public class DrawbackManager {

	@Autowired
	private DrawbackRepository drawbackRepository;
	@Autowired
	private PufaPayService pufaPayService;
	
	/**
	 * 用户发起退款申请
	 * @param goodOrder
	 * @return
	 */
	public Drawback drawback(GoodOrder order, String reason) {
		OrderStatus status=order.getStatus();
		if(!(status==OrderStatus.PAYED || status==OrderStatus.PROCESSING)) {
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "订单所处状态不允许退款操作");
		}
		
		Drawback drawback=new Drawback();
		drawback.setOrder(order);
		drawback.setReason(reason);
		drawback.setStatus(DrawbackStatus.WAITING);
		drawback.setOrderStatus(order.getStatus());
		drawback=drawbackRepository.save(drawback);
		
		order.setStatus(OrderStatus.DRAWBACKING);
		return drawback;
	}
	
	/**批准退款并执行退款操作*/
	public Drawback approve(Drawback drawback, User authenticater) {
		drawback.setAuthenticater(authenticater);
		drawback.setProcessDate(new Date());
		drawback.setStatus(DrawbackStatus.APPROVE);
		
		drawbackMoney(drawback.getOrder());
		return drawback;
	}
	
	/**拒绝退款并修改订单状态*/
	public Drawback disapprove(Drawback drawback, User authenticater, String reason) {
		drawback.setAuthenticater(authenticater);
		drawback.setProcessDate(new Date());
		drawback.setStatus(DrawbackStatus.DISAPPROVE);
		drawback.setReasonForDisapprove(reason);
		drawback.getOrder().setStatus(drawback.getOrderStatus());//恢复订单退款申请之前的状态
		return drawback;
	}
	
	private void drawbackMoney(GoodOrder order) {
		OrderPay orderPay=order.getPayment();
		PufaPay pay=order.getPayment().getPufaPay();
		pufaPayService.drawback(pay);
		orderPay.setRefundMoney(pay.getTranAmt());
		order.setStatus(OrderStatus.DRAWBACKED);
	}
}
