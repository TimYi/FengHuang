package org.sharechina.pay.pufa.protocal.pay;

import org.sharechina.pay.pufa.protocal.PlainData;

public class KhzfResponseData {
	
	public static KhzfResponseData fromPlain(String plain) {
		return PlainData.fromPlain(KhzfResponseData.class, plain);
	}

	/**交易缩写*/
	private String TranAbbr;
	/**网关流水，要求记录此值，撤销、退货等交易需要该字段*/
	private String AcqSsn;
	/**商户日期时间*/
	private String MercDtTm;
	/**订单号*/
	private String TermSsn;
	/**响应码*/
	private String RespCode;
	/**终端号*/
	private String TermCode;
	/**商户号*/
	private String MercCode;
	/**交易金额(元)*/
	private Double TranAmt;
	/**清算日期，要求记录此值，撤销、退货等交易需要该字段*/
	private String ClearDate;
	
	public String getTranAbbr() {
		return TranAbbr;
	}
	public void setTranAbbr(String tranAbbr) {
		TranAbbr = tranAbbr;
	}
	public String getAcqSsn() {
		return AcqSsn;
	}
	public void setAcqSsn(String acqSsn) {
		AcqSsn = acqSsn;
	}
	public String getMercDtTm() {
		return MercDtTm;
	}
	public void setMercDtTm(String mercDtTm) {
		MercDtTm = mercDtTm;
	}
	public String getTermSsn() {
		return TermSsn;
	}
	public void setTermSsn(String termSsn) {
		TermSsn = termSsn;
	}
	public String getRespCode() {
		return RespCode;
	}
	public void setRespCode(String respCode) {
		RespCode = respCode;
	}
	public String getTermCode() {
		return TermCode;
	}
	public void setTermCode(String termCode) {
		TermCode = termCode;
	}
	public String getMercCode() {
		return MercCode;
	}
	public void setMercCode(String mercCode) {
		MercCode = mercCode;
	}
	public Double getTranAmt() {
		return TranAmt;
	}
	public void setTranAmt(Double tranAmt) {
		TranAmt = tranAmt;
	}
	public String getClearDate() {
		return ClearDate;
	}
	public void setClearDate(String clearDate) {
		ClearDate = clearDate;
	}
}
