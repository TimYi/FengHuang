package org.sharechina.pay.pufa.protocal;

import java.lang.reflect.Field;

import org.junit.Test;
import org.sharechina.pay.pufa.protocal.pay.AccountType;
import org.sharechina.pay.pufa.protocal.pay.KhzfRequestData;
import org.sharechina.pay.pufa.protocal.pay.KhzfResponseData;
import org.sharechina.pay.pufa.protocal.pay.PayBank;
import org.sharechina.pay.pufa.protocal.pay.PayType;

public class RequestDataTest {

	//@Test
	public void requestDataToXml() {
		SimplePlainRequestData data=new SimplePlainRequestData();
		requestDataToRequestXml(data);
	}
	
	//@Test
	public void khzfRequest() {
		KhzfRequestData data=new KhzfRequestData(null, "010101010101", "test", null, 88.0, PayBank.abc, 
				AccountType.JIEJJI, PayType.BUY, "test", "test", "test", "test", null, null);
		requestDataToRequestXml(data);
	}
	
	@Test
	public void getDataFromXml() {
		String xml="<packet>\n<transName>KHCX</transName>\n<Plain>name=testdata|title=≤‚ ‘ƒ⁄»›</Plain>\n<Signature>1a80cc82d56cc32e3a2816c351f07c775b0d242d6eb1a36995564f87db0850ff8b6ee8174d9256ac2d9e22b9131f9bcbfe2ffc6b78eabd38c4265890b3069a3ae20ef55745ff7a7428d8e96951fca915990252cd2e0cb26654ac75f96421ad38b1c827ca3e19dbd64762be7b0bf5f44d87b14192feaebb2065d61fae8ab5a415</Signature>\n</packet>";
		printDataFromXml(xml);
		xml="<packet>\n<ErrorCode>¥ÌŒÛ¬Î</ErrorCode>\n<ErrorMsg>¥ÌŒÛ–≈œ¢</ErrorMsg>\n</packet>";
		printDataFromXml(xml);
	}
	
	//@Test
	public void fromPlain() {
		String plain="name=testdata|title=≤‚ ‘ƒ⁄»›";
		SimplePlainData data=PlainData.fromPlain(SimplePlainData.class, plain);
		System.out.println(data.getName());
		System.out.println(data.getTitle());
	}
	
	//@Test
	public void khzfResponseDataFromPlain() {
		String plain="TranAbbr=KHZF|AcqSsn=0101010101|TermSsn=12345678";
		KhzfResponseData data=KhzfResponseData.fromPlain(plain);
		printEachField(data);
	}
	
	private void requestDataToRequestXml(RequestData data) {
		RequestModel requestData=new RequestModel(data);
		String xmlString=requestData.toXml();
		System.out.println(xmlString);
	}
	
	private void printDataFromXml(String xml) {
		try {
			ResponseModel<Object> response=ResponseModel.fromXml(xml,Object.class);
			if(response.isSuccess()) {
				System.out.println(response.getPlain());
				System.out.println(response.getSignature());
			} else {
				System.out.println(response.getErrorCode());
				System.out.println(response.getErrorMsg());				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void printEachField(Object obj) {
		for (Field field : obj.getClass().getDeclaredFields()) {			
			try {
				field.setAccessible(true);
				Object value=field.get(obj);
				if(value!=null) {
					System.out.print(field.getName()+":");
					System.out.println(value);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
	}
}
