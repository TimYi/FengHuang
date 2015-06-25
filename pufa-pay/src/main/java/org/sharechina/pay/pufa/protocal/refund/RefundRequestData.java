package org.sharechina.pay.pufa.protocal.refund;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.sharechina.pay.pufa.protocal.PlainData;
import org.sharechina.pay.pufa.protocal.RequestData;
import org.sharechina.pay.pufa.protocal.TransName;

/**跨行退货请求参数*/
public class RefundRequestData implements RequestData {
	
	private KhthPlainData plainData;

	@Override
	public TransName getTransName() {
		return TransName.KHTH;
	}

	@Override
	public PlainData getPlainData() {
		return plainData;
	}
	
	/**
	 * 
	 * @param masterId	企业客户号，企业客户支付时必须提供，个人客户支付时不需要
	 * @param termSsn	订单号，不能重复
	 * @param osttDate	原交易的清算日期，从支付回调结果中记录
	 * @param oacqSsn	原交易的网关流水，从支付回调结果中记录
	 * @param mercCode	商户号，开户时获得
	 * @param termCode	终端号，可以全为0，已经提供了全为0的默认值
	 * @param tranAmt	交易金额，单位为元
	 * @param remark1	交易备注1，交易备注一，该字段将在对账文件中提供
	 * @param remark2	交易备注2，交易备注二，该字段将在对账文件中提供
	 */
	public RefundRequestData(String masterId, String termSsn, String osttDate, String oacqSsn, String mercCode,
				String termCode, Double tranAmt, String remark1, String remark2) {
		KhthPlainData data=new KhthPlainData(masterId, termSsn, osttDate, oacqSsn, mercCode, termCode, tranAmt,
				remark1, remark2);
		this.plainData=data;
	}
	
	public static class KhthPlainData implements PlainData {
		
		/**
		 * 
		 * @param masterId	企业客户号，企业客户支付时必须提供，个人客户支付时不需要
		 * @param termSsn	订单号，不能重复
		 * @param osttDate	原交易的清算日期，从支付回调结果中记录
		 * @param oacqSsn	原交易的网关流水，从支付回调结果中记录
		 * @param mercCode	商户号，开户时获得
		 * @param termCode	终端号，可以全为0，已经提供了全为0的默认值
		 * @param tranAmt	交易金额，单位为元
		 * @param remark1	交易备注1，交易备注一，该字段将在对账文件中提供
		 * @param remark2	交易备注2，交易备注二，该字段将在对账文件中提供
		 */
		public KhthPlainData(String masterId, String termSsn, String osttDate, String oacqSsn, String mercCode,
				String termCode, Double tranAmt, String remark1, String remark2) {
			this.MasterId=masterId;
			this.TermSsn=termSsn;
			this.OSttDate=osttDate;
			this.OAcqSsn=oacqSsn;
			this.MercCode=mercCode;
			if(termCode!=null) {
				this.TermCode=termCode;
			}			
			this.TranAmt=tranAmt;
			this.Remark1=remark1;
			this.Remark2=remark2;
		}
		
		/**交易缩写*/
		private final TransName TranAbbr=TransName.KHTH;
		/**企业客户号，企业客户支付时必须提供，个人客户支付时不需要*/
		private String MasterId;
		/**商户日期时间，yyyyMMddhhMMss*/
		private LocalDateTime MercDtTm=LocalDateTime.now();
		/**订单号，不能重复*/
		private String TermSsn;
		/**原交易的清算日期，从支付回调结果中记录*/
		private String OSttDate;
		/**原交易的网关流水，从支付回调结果中记录*/
		private String OAcqSsn;
		/**商户号，开户时获得*/
		private String MercCode;		
		/**终端号，可以全为0*/
		private String TermCode="00000000";
		/**交易金额，单位为元*/
		private Double TranAmt;
		/**交易备注1，交易备注一，该字段将在对账文件中提供*/
		private String Remark1;
		/**交易备注2，交易备注二，该字段将在对账文件中提供*/
		private String Remark2;
		
		public TransName getTranAbbr() {
			return TranAbbr;
		}
		public String getMasterId() {
			return MasterId;
		}
		public String getMercDtTm() {
			return MercDtTm.format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
		}
		public String getTermSsn() {
			return TermSsn;
		}
		public String getOSttDate() {
			return OSttDate;
		}
		public String getOAcqSsn() {
			return OAcqSsn;
		}
		public String getMercCode() {
			return MercCode;
		}
		public String getTermCode() {
			return TermCode;
		}
		public Double getTranAmt() {
			return TranAmt;
		}
		public String getRemark1() {
			return Remark1;
		}
		public String getRemark2() {
			return Remark2;
		}		
	}

}
