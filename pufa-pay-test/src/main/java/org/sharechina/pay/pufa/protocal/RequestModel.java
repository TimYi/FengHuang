package org.sharechina.pay.pufa.protocal;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import com.csii.payment.client.core.MerchantSignVerify;
import com.thoughtworks.xstream.XStream;

/**
 * 通用请求模型
 * @author pc
 *
 */
public class RequestModel {
	
	private static final String requestHeader="<?xml version=\"1.0\" encoding=\"GBK\"?>\n";
	
	private static final XStream xStreamForRequestPostData;
	
	static {
		xStreamForRequestPostData = new XStream();
		xStreamForRequestPostData.alias("packet", RequestModel.class);
	}

	/**交易名*/
	private TransName transName;
	/**明文信息*/
	private String Plain;
	/**签名*/
	private String Signature;
	
	public RequestModel(RequestData data) {
		this.transName=data.getTransName();
		this.Plain=data.getPlainData().toPlain();
		this.Signature=MerchantSignVerify.merchantSignData_ABA(Plain);		
	}
	
	/**转化为浦发标准格式的xml数据*/
	public String toXml() {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Writer writer=null;
		try {
			writer = new OutputStreamWriter(outputStream, "GBK");
			writer.write(requestHeader);
		} catch (Exception e) {
		}
		xStreamForRequestPostData.toXML(this,writer);
		try {
			return outputStream.toString("GBK");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
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
	
	
}
