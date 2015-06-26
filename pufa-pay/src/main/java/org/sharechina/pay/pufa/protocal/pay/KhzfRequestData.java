package org.sharechina.pay.pufa.protocal.pay;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.sharechina.pay.pufa.protocal.AccountType;
import org.sharechina.pay.pufa.protocal.PayBank;
import org.sharechina.pay.pufa.protocal.PayType;
import org.sharechina.pay.pufa.protocal.PlainData;
import org.sharechina.pay.pufa.protocal.RequestData;
import org.sharechina.pay.pufa.protocal.TransName;

public class KhzfRequestData implements RequestData {

	private KhzfPlainData plainData;

	public TransName getTransName() {
		return TransName.KHZF;
	}
	public KhzfPlainData getPlainData() {
		return plainData;
	}
	
	/**
	 * 
	 * @param MasterId		企业客户号，企业客户支付时必须提供，个人客户支付时不需要
	 * @param TermSsn		订单号，不能重复
	 * @param MercCode		商户号，开户时获得
	 * @param TermCode		终端号，可以全为0
	 * @param TranAmt		交易金额，单位为元
	 * @param PayBank		支付银行
	 * @param AccountType	账户类型
	 * @param PayType		支付类型
	 * @param Subject		商品名称，商品的标题/交易标题/订单标题/订单关键字等。该参数最长为128个汉字。
	 * @param Notice		商品描述，对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
	 * @param Remark1		交易备注1，交易备注一，该字段将在对账文件中提供
	 * @param Remark2		交易备注2，交易备注二，该字段将在对账文件中提供
	 * @param MercUrl		支付交易中，接收交易结果的url;如果该项为空，则交易结果会送到商户在柜面签约的时候填写的接收结果的url；否则就会送到该地址中。
	 * @param Ip			订单生产时的IP地址，如果该项为空，则不进行IP地址检查。
	 */
	public KhzfRequestData(String MasterId, String TermSsn, String MercCode, String TermCode, Double TranAmt,
				PayBank PayBank, AccountType AccountType, PayType PayType, String Subject, String Notice,
				String Remark1, String Remark2, URL MercUrl, String Ip) {
		KhzfPlainData data=new KhzfPlainData(MasterId, TermSsn, MercCode, TermCode, TranAmt, PayBank, 
				AccountType, PayType, Subject, Notice, Remark1, Remark2, MercUrl, Ip);
		this.plainData=data;
	}

	public static class KhzfPlainData implements PlainData {		
		/**
		 * 
		 * @param MasterId		企业客户号，企业客户支付时必须提供，个人客户支付时不需要
		 * @param TermSsn		订单号，不能重复
		 * @param MercCode		商户号，开户时获得
		 * @param TermCode		终端号，可以全为0
		 * @param TranAmt		交易金额，单位为元
		 * @param PayBank		支付银行
		 * @param AccountType	账户类型
		 * @param PayType		支付类型
		 * @param Subject		商品名称，商品的标题/交易标题/订单标题/订单关键字等。该参数最长为128个汉字。
		 * @param Notice		商品描述，对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
		 * @param Remark1		交易备注1，交易备注一，该字段将在对账文件中提供
		 * @param Remark2		交易备注2，交易备注二，该字段将在对账文件中提供
		 * @param MercUrl		支付交易中，接收交易结果的url;如果该项为空，则交易结果会送到商户在柜面签约的时候填写的接收结果的url；否则就会送到该地址中。
		 * @param Ip			订单生产时的IP地址，如果该项为空，则不进行IP地址检查。
		 */
		public KhzfPlainData(String MasterId, String TermSsn, String MercCode, String TermCode, Double TranAmt,
				PayBank PayBank, AccountType AccountType, PayType PayType, String Subject, String Notice,
				String Remark1, String Remark2, URL MercUrl, String Ip) {
			this.MasterId=MasterId;
			this.TermSsn=TermSsn;
			this.MercCode=MercCode;
			this.TermCode=TermCode;
			if(StringUtils.isBlank(TermCode)) {
				this.TermCode="00000000";
			}
			this.TranAmt=TranAmt;
			this.PayBank=PayBank;
			this.AccountType=AccountType;
			this.PayType=PayType;
			this.Subject=Subject;
			this.Notice=Notice;
			this.Remark1=Remark1;
			this.Remark2=Remark2;
			this.MercUrl=MercUrl;
			this.Ip=Ip;
		}
		
		
		/**交易缩写*/
		private TransName TranAbbr=TransName.KHZF;
		/**商户日期时间，yyyyMMddhhMMss*/
		private LocalDateTime MercDtTm=LocalDateTime.now();
		/**企业客户号，企业客户支付时必须提供，个人客户支付时不需要*/
		private String MasterId;		
		/**订单号，不能重复*/
		private String TermSsn;
		/**商户号，开户时获得*/
		private String MercCode;
		/**终端号，可以全为0*/
		private String TermCode="00000000";
		/**交易金额，单位为元*/
		private Double TranAmt;
		/**支付银行*/
		private PayBank PayBank;
		/**账户类型*/
		private AccountType AccountType;
		/**支付类型*/
		private PayType PayType;
		/**商品名称，商品的标题/交易标题/订单标题/订单关键字等。该参数最长为128个汉字。*/
		private String Subject;
		/**商品描述，对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。*/
		private String Notice;
		/**交易备注1，交易备注一，该字段将在对账文件中提供*/
		private String Remark1;
		/**交易备注2，交易备注二，该字段将在对账文件中提供*/
		private String Remark2;
		/**支付交易中，接收交易结果的url;如果该项为空，则交易结果会送到商户在柜面签约的时候填写的接收结果的url；否则就会送到该地址中。*/
		private URL MercUrl;
		/**订单生产时的IP地址，如果该项为空，则不进行IP地址检查。*/
		private String Ip;
		
		public TransName getTranAbbr() {
			return TranAbbr;
		}		
		public String getMercDtTm() {
			return MercDtTm.format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
		}
		public String getMasterId() {
			return MasterId;
		}
		public String getTermSsn() {
			return TermSsn;
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
		public PayBank getPayBank() {
			return PayBank;
		}
		public AccountType getAccountType() {
			return AccountType;
		}
		public PayType getPayType() {
			return PayType;
		}
		public String getSubject() {
			return Subject;
		}
		public String getNotice() {
			return Notice;
		}
		public String getRemark1() {
			return Remark1;
		}
		public String getRemark2() {
			return Remark2;
		}
		public URL getMercUrl() {
			return MercUrl;
		}
		public String getIp() {
			return Ip;
		}
	}
}
