package com.fenghuangzhujia.eshop.commerce.drawback;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fenghuangzhujia.eshop.commerce.order.GoodOrder;
import com.fenghuangzhujia.eshop.commerce.order.GoodOrder.OrderStatus;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

/**
 * 退款申请
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_drawback")
public class Drawback extends UUIDBaseModel {

	/**申请退款的订单*/
	private GoodOrder order;
	/**用户输入退款原因*/
	private String reason;
	/**不同意退款原因*/
	private String reasonForDisapprove;
	/**审核处理人*/
	private String authenticater;
	/**处理日期*/
	private Date processDate;
	/**退款状态*/
	private DrawbackStatus status;
	/**退款申请前的订单状态*/
	private OrderStatus orderStatus;
		
	/**退款申请所属用户*/
	@Transient
	public User getUser() {
		return getOrder().getUser();
	}
	@JoinColumn(name="drawback_order")
	@OneToOne(optional=false)
	public GoodOrder getOrder() {
		return order;
	}
	public void setOrder(GoodOrder order) {
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
	public String getAuthenticater() {
		return authenticater;
	}
	public void setAuthenticater(String authenticater) {
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

	public static enum DrawbackStatus {
		WAITING,
		APPROVE,
		DISAPPROVE;
	}
}
