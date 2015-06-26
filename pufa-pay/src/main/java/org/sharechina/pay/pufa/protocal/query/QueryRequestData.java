package org.sharechina.pay.pufa.protocal.query;

import org.sharechina.pay.pufa.protocal.PlainData;
import org.sharechina.pay.pufa.protocal.RequestData;
import org.sharechina.pay.pufa.protocal.TransName;

/**
 * 单笔查询
 * @author pc
 *
 */
public class QueryRequestData implements RequestData {
	
	private KhcxPlainData plainData;

	@Override
	public TransName getTransName() {
		return TransName.KHCX;
	}

	@Override
	public PlainData getPlainData() {
		return plainData;
	}
	
	/**
	 * 
	 * @param mercCode	商户号，开户时获得
	 * @param OTranAbbr	原交易缩写
	 * @param termSsn	订单号，不能重复
	 */
	public QueryRequestData(String mercCode, TransName OTranAbbr, String termSsn) {
		plainData=new KhcxPlainData(mercCode, OTranAbbr, termSsn);
	}
	
	public static class KhcxPlainData implements PlainData {
		
		/**
		 * 
		 * @param mercCode	商户号，开户时获得
		 * @param OTranAbbr	原交易缩写
		 * @param termSsn	订单号，不能重复
		 */
		public KhcxPlainData(String mercCode, TransName OTranAbbr, String termSsn) {
			this.MercCode=mercCode;
			this.OTranAbbr=OTranAbbr;
			this.TermSsn=termSsn;
		}
		
		/**商户号，开户时获得*/
		private String MercCode;
		/**原交易缩写*/
		private TransName OTranAbbr;
		/**订单号，不能重复*/
		private String TermSsn;
		public String getMercCode() {
			return MercCode;
		}
		public TransName getOTranAbbr() {
			return OTranAbbr;
		}
		public String getTermSsn() {
			return TermSsn;
		}
	}
}
