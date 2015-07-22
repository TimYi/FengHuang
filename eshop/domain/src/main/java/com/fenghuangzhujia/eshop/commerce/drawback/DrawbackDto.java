package com.fenghuangzhujia.eshop.commerce.drawback;

import java.util.Date;

import com.fenghuangzhujia.eshop.commerce.drawback.Drawback.DrawbackStatus;
import com.fenghuangzhujia.eshop.commerce.order.GoodOrder.OrderStatus;
import com.fenghuangzhujia.eshop.commerce.order.dto.GoodOrderDto;
import com.fenghuangzhujia.eshop.core.user.dto.UserDto;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class DrawbackDto extends DtoBaseModel {

	/**申请退款的订单*/
	private GoodOrderDto order;
	/**用户输入退款原因*/
	private String reason;
	/**不同意退款原因*/
	private String reasonForDisapprove;
	/**审核处理人*/
	private UserDto authenticater;
	/**处理日期*/
	private Date processDate;
	/**退款状态*/
	private DrawbackStatus status;
	/**退款申请前的订单状态*/
	private OrderStatus orderStatus;
	
	public GoodOrderDto getOrder() {
		return order;
	}
	public void setOrder(GoodOrderDto order) {
		this.order = order;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getReasonForDisapprove() {
		return reasonForDisapprove;
	}
	public void setReasonForDisapprove(String reasonForDisapprove) {
		this.reasonForDisapprove = reasonForDisapprove;
	}
	public UserDto getAuthenticater() {
		return authenticater;
	}
	public void setAuthenticater(UserDto authenticater) {
		this.authenticater = authenticater;
	}
	public Date getProcessDate() {
		return processDate;
	}
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}
	public DrawbackStatus getStatus() {
		return status;
	}
	public void setStatus(DrawbackStatus status) {
		this.status = status;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
}
