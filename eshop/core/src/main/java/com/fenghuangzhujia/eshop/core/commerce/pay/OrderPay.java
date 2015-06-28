package com.fenghuangzhujia.eshop.core.commerce.pay;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.core.commerce.coupons.Coupons;
import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrder;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

/**
 * 代表一次订单支付
 * @author ytm
 *
 */
@Entity
@Table(name="fhzj_order_pay")
public class OrderPay extends UUIDBaseModel {

	/**应支付总额*/
	private Double totalMoney=0.0;
	/**优惠金额*/
	private Double couponsMoney=0.0;
	/**除去优惠金额后，应支付总额*/
	private Double shouldPayMoney=0.0;
	/**已经支付金额*/
	private Double payedMoney=0.0;
	/**退款金额*/
	private Double refundMoney=0.0;
	/**是否已经支付*/
	private boolean hasPayed;
	/**支付对应的订单*/
	private GoodOrder order;
	/**实际支付信息（暂时只有浦发支付）*/
	private PufaPay pufaPay;
	/**使用的优惠券*/
	private Set<Coupons> couponses;
	
	public Double getTotalMoney() {
		return totalMoney;
	}
	public Double getCouponsMoney() {
		return couponsMoney;
	}
	public Double getShouldPayMoney() {
		return shouldPayMoney;
	}
	public Double getPayedMoney() {
		return payedMoney;
	}
	public Double getRefundMoney() {
		return refundMoney;
	}
	public boolean isHasPayed() {
		return hasPayed;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public void setCouponsMoney(Double couponsMoney) {
		this.couponsMoney = couponsMoney;
	}
	public void setShouldPayMoney(Double shouldPayMoney) {
		this.shouldPayMoney = shouldPayMoney;
	}
	public void setPayedMoney(Double payedMoney) {
		this.payedMoney = payedMoney;
	}
	public void setRefundMoney(Double refundMoney) {
		this.refundMoney = refundMoney;
	}
	public void setHasPayed(boolean hasPayed) {
		this.hasPayed = hasPayed;
	}
	@OneToOne(mappedBy="payment")
	public GoodOrder getOrder() {
		return order;
	}
	public void setOrder(GoodOrder order) {
		this.order = order;
	}
	@OneToOne(cascade=CascadeType.ALL)
	public PufaPay getPufaPay() {
		return pufaPay;
	}
	public void setPufaPay(PufaPay pufaPay) {
		this.pufaPay = pufaPay;
	}
	@ManyToMany
	@JoinTable(name="fhzj_order_pay_coupons")
	public Set<Coupons> getCouponses() {
		return couponses;
	}	
	public void setCouponses(Set<Coupons> couponses) {
		this.couponses = couponses;
	}
}
