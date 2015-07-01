package org.sharechina.pay.pufa.protocal;

import java.security.SignatureException;

import org.sharechina.pay.pufa.common.XmlUtil;

import com.csii.payment.client.core.MerchantSignVerify;

/**
 * 浦发返回的标准格式数据，封装正确和错误两种格式
 * @author pc
 *
 */
public class ResponseModel<T> {
	
	private boolean success;
	
	/**
	 * 是否是成功返回
	 * if true, 可以获取transName,Plain,Signature,data
	 * if false, 获取ErrorCode,ErrorMsg
	 * @return
	 */
	public boolean isSuccess() {
		return success;
	}
	
	/**以下信息错误时返回*/
	private String ErrorCode;
	private String ErrorMsg;
	
	/**以下信息正确时返回*/
	private TransName transName;
	private String Plain;
	private String Signature;
	private T data;
	
	/**
	 * 从返回xml数据中解析返回结果
	 * @param body
	 * @return
	 */
	public static <T> ResponseModel<T> fromXml(String xml, Class<T> tClass) throws SignatureException {
		@SuppressWarnings("unchecked")
		ResponseModel<T> response=XmlUtil.getObjectFromXml(xml, ResponseModel.class, "packet", null);
		if(response.getErrorCode()==null) {
			response.success(tClass);
			return response;
		}
		response.fail();
		return response;
	}
	
	private ResponseModel(){}
	
	private void success(Class<T> tClass) throws SignatureException {
		this.success=true;
		boolean isSignSuccess=MerchantSignVerify.merchantVerifyPayGate_ABA(Signature, Plain);
		if(!isSignSuccess)throw new SignatureException();
		data=PlainData.fromPlain(tClass, Plain);		
	}
	
	private void fail() {
		this.success=false;
	}
	
	public String getErrorCode() {
		return ErrorCode;
	}
	public String getErrorMsg() {
		return ErrorMsg;
	}
	public TransName getTransName() {
		return transName;
	}
	public String getPlain() {
		return Plain;
	}
	public String getSignature() {
		return Signature;
	}
	public T getData() {
		return data;
	}
}
