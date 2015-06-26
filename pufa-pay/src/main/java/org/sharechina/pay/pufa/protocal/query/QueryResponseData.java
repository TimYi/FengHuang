package org.sharechina.pay.pufa.protocal.query;

public class QueryResponseData {

	/**订单号*/
	private String TermSsn;
	/**交易金额(元)*/
	private Double TranAmt;
	/**
	 * 00-交易成功
	 * 01-交易失败
	 * 02-撤销成功
	 * 03-部分退货
	 * 04-全部退货
	 * 05-退货待受理
	 * 06-退货受理成功
	 * 09-支付中
	 * 99-交易超时
	 */
	private String TransStatus;
	/**响应码*/
	private String RespCode;
	/**清算日期*/
	private String ClearDate;
	/**网管流水*/
	private String AcqSsn;
	
	public String getTermSsn() {
		return TermSsn;
	}
	public void setTermSsn(String termSsn) {
		TermSsn = termSsn;
	}
	public Double getTranAmt() {
		return TranAmt;
	}
	public void setTranAmt(Double tranAmt) {
		TranAmt = tranAmt;
	}
	public String getTransStatus() {
		return TransStatus;
	}
	public void setTransStatus(String transStatus) {
		TransStatus = transStatus;
	}
	public String getRespCode() {
		return RespCode;
	}
	public void setRespCode(String respCode) {
		RespCode = respCode;
	}
	public String getClearDate() {
		return ClearDate;
	}
	public void setClearDate(String clearDate) {
		ClearDate = clearDate;
	}
	public String getAcqSsn() {
		return AcqSsn;
	}
	public void setAcqSsn(String acqSsn) {
		AcqSsn = acqSsn;
	}
}
