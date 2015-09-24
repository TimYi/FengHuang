package org.sharechina.pay.pufa.protocal.download;

import org.sharechina.pay.pufa.protocal.PlainData;
import org.sharechina.pay.pufa.protocal.RequestData;
import org.sharechina.pay.pufa.protocal.TransName;

public class KhxzRequestData implements RequestData {

	private KhxzPlainData plainData;
	
	public TransName getTransName() {
		return TransName.KHXZ;
	}
	
	@Override
	public KhxzPlainData getPlainData() {
		return plainData;
	}
	
	/**
	 * 
	 * @param MercCode	商户号
	 * @param OSttdate	清算日期
	 * @param SetFType	清算文件类型
	 */
	public KhxzRequestData(String MercCode, String OSttDate, SetFType SetFType) {
		KhxzPlainData data=new KhxzPlainData(MercCode, OSttDate, SetFType);
		this.plainData=data;
	}
	
	public static class KhxzPlainData implements PlainData {
		
		public KhxzPlainData(String MercCode, String OSttDate, SetFType SetFType) {
			this.MercCode=MercCode;
			this.OSttDate=OSttDate;
			this.SetFType=SetFType;
		}
		
		private String MercCode;
		private String OSttDate;
		private SetFType SetFType;
		
		public String getMercCode() {
			return MercCode;
		}
		public void setMercCode(String mercCode) {
			MercCode = mercCode;
		}
		public String getOSttDate() {
			return OSttDate;
		}
		public void setOSttDate(String oSttDate) {
			OSttDate = oSttDate;
		}
		public Integer getSetFType() {
			return SetFType.ordinal();
		}
		public void setSetFType(SetFType setFType) {
			SetFType = setFType;
		}
	}
	
	public static enum SetFType {
		/**
		 * 全部
		 */
		ALL,
		/**
		 * 支付
		 */
		PAY,
		/**
		 * 退款
		 */
		DRAWBACK;
	}
}
