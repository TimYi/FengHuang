package org.sharechina.pay.pufa.protocal;

import java.security.SignatureException;

import org.junit.Test;
import org.sharechina.pay.pufa.protocal.pay.AccountType;
import org.sharechina.pay.pufa.protocal.pay.KhzfResponseData;
import org.sharechina.pay.pufa.protocal.pay.PayBank;
import org.sharechina.pay.pufa.protocal.pay.PayType;
import org.sharechina.pay.pufa.service.KhzfService;

public class KhzfServiceTest {

	@Test
	public void getKhzfParam() {
		
		RequestModel kfzfModel=KhzfService.getKhzfRequestData(null, "010101010101", "983708160009501", null, 88.0, 
				PayBank.abc, AccountType.JIEJJI, PayType.BUY, "test", "test", "test", "test", null, null);
		RequestDataTest.printEachField(kfzfModel);
	}
	
	
	public void resolveKhzfXml() {
		String xml="<packet>\n<transName>KHZF</transName>\n<Plain>TranAbbr=KHZF|AcqSsn=0101010101|TermSsn=12345678</Plain>\n<Signature>1a80cc82d56cc32e3a2816c351f07c775b0d242d6eb1a36995564f87db0850ff8b6ee8174d9256ac2d9e22b9131f9bcbfe2ffc6b78eabd38c4265890b3069a3ae20ef55745ff7a7428d8e96951fca915990252cd2e0cb26654ac75f96421ad38b1c827ca3e19dbd64762be7b0bf5f44d87b14192feaebb2065d61fae8ab5a415</Signature>\n</packet>";
		ResponseModel<KhzfResponseData> data;
		try {
			data = KhzfService.resolveFromXml(xml);
			RequestDataTest.printEachField(data);
		} catch (SignatureException e) {
			e.printStackTrace();
		}		
	}
}
