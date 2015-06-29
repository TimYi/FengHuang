package com.fenghuangzhujia.eshop.core.commerce.pay;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

/**
 * 代表一次浦发支付记录
 * @author ytm
 *
 */
@Entity
@Table(name="fhzj_pay_pufa")
public class PufaPay extends UUIDBaseModel {

	/**网管流水*/
	private String acqSsn;
	/**订单编号*/
	private String termSsn;
	/**交易金额*/
	private Double tranAmt;
	/**清算日期*/
	private String clearDate;
	/**记录支付时间*/
	private Date payTime;
	/**是否是一个支付完成的订单*/
	private boolean hasPayed;
	
	public String getAcqSsn() {
		return acqSsn;
	}
	@Column(unique=true)
	public String getTermSsn() {
		return termSsn;
	}
	public Double getTranAmt() {
		return tranAmt;
	}
	public String getClearDate() {
		return clearDate;
	}
	public void setAcqSsn(String acqSsn) {
		this.acqSsn = acqSsn;
	}
	public void setTermSsn(String termSsn) {
		this.termSsn = termSsn;
	}
	public void setTranAmt(Double tranAmt) {
		this.tranAmt = tranAmt;
	}
	public void setClearDate(String clearDate) {
		this.clearDate = clearDate;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public boolean isHasPayed() {
		return hasPayed;
	}
	public void setHasPayed(boolean hasPayed) {
		this.hasPayed = hasPayed;
	}
}
