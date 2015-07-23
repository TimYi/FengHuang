package com.fenghuangzhujia.eshop.commerce.order;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fenghuangzhujia.eshop.commerce.goods.Good;
import com.fenghuangzhujia.eshop.commerce.pay.OrderPay;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContent;

/**
 * 商品订单
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_good_order")
public class GoodOrder extends UUIDBaseModel {
	
	private User user;
	/**购买时实际价格*/
	private double price;
	/**
	 * 订单状态
	 * TODO 完善订单生命周期
	 */
	private OrderStatus status;
	/**购买的商品*/
	private Good good;
	/**购买数量*/
	private Integer count;
	/**用户手机号码*/
	private String mobile;
	/**用户真实姓名*/
	private String realName;
	/**支付详情*/
	private OrderPay payment;
	/**订单流水号，业务代码+区号+日期+随机尾号*/
	private String code;
	/**订单来源*/
	private SourceType source;
	
	
	@Column(unique=true)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 商品名称
	 * @return
	 */
	@Transient
	public String getName() {
		return good.getName();
	}
	
	/**
	 * 商品类型
	 * @return
	 */
	@Transient
	public String getType() {
		return good.getType();
	}
	
	/**
	 * 商品主图
	 * @return
	 */
	@Transient
	public MediaContent getMainPic() {
		if(good==null)return null;
		return good.getMainPic();
	}
	
	@Transient
	public Date getPayTime() {
		if(getPayment()==null)return null;
		return getPayment().getPayTime();
	}
	
	@ManyToOne(optional=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 订单状态
	 * @return
	 */
	@Enumerated
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * 商品
	 * @return
	 */
	@ManyToOne(optional=false)
	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
		this.good = good;
	}

	/**
	 * 购买总数
	 * @return
	 */
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获得用户手机号码
	 * @return
	 */
	public String getMobile() {
		return this.mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	@OneToOne(cascade=CascadeType.ALL)
	public OrderPay getPayment() {
		return payment;
	}
	public void setPayment(OrderPay payment) {
		this.payment = payment;
	}
	public SourceType getSource() {
		return source;
	}
	public void setSource(SourceType source) {
		this.source = source;
	}




	public static enum OrderStatus {
		/**等待支付*/
		WAITING,
		/**用户支付完成*/
		PAYED,
		/**后台和用户沟通处理开始，到整个家装过程结束*/
		PROCESSING,
		/**已完成*/
		COMPLETE,
		/**已取消，用于用户未付款时*/
		CANCEL,	
		/**退款申请中*/
		DRAWBACKING,
		/**已退款*/
		DRAWBACKED;
	}
	
	public static enum SourceType {
		/**电脑端*/
		PCWEB,
		/**手机网页*/
		MOBILE,
		/**手机app*/
		APP;
	}
}
