package org.sharechina.pay.pufa.service;

import java.security.SignatureException;

import org.junit.Test;
import org.sharechina.pay.pufa.protocal.AccountType;
import org.sharechina.pay.pufa.protocal.PayBank;
import org.sharechina.pay.pufa.protocal.PayType;
import org.sharechina.pay.pufa.protocal.RequestModel;
import org.sharechina.pay.pufa.protocal.pay.KhzfResponseData;
import org.sharechina.pay.pufa.service.KhzfService;
import org.sharechina.pay.pufa.utils.TestUtils;

public class KhzfServiceTest {

	//@Test
	public void getKhzfParam() {
		
		RequestModel kfzfModel=KhzfService.getKhzfRequestData(null, "010101010101", "983708160009501", null, 88.0, 
				PayBank.abc, AccountType.JIEJJI, PayType.BUY, "test", "test", "test", "test", null, null);
		TestUtils.printEachField(kfzfModel);
	}
	
	@Test
	public void resolveKhzfXml() {
		String plain="TranAbbr%3DKHTZ%7CAcqSsn%3D100000922674%7CMercDtTm%3D20150702093000%7CTermSsn%3D20150702CfLb%7CTermCode%3D%7CMercCode%3D983708160009501%7CTranAmt%3D2000.00%7CClearDate%3D20150702%7CRespCode%3D00";
		String signature="440ebd1567fdb988b5f9541475a9b05f6561284f38889555b6682a10fa23bcab17a18719791df2b09aaa4acbac00da44a5e56e82cf44a38c1df9be1450479338e679b8bb80dd8b00c0d465ec864d3ea6facff8778ac73171a3e33e6df4c1551f8153888974665208a4d553fe98f02a358f5e21efe16a372261c82795470d2c05";
		KhzfResponseData data;
		try {
			data = KhzfService.resolveKhzfResult(plain,signature);
			TestUtils.printEachField(data);
		} catch (SignatureException e) {
			e.printStackTrace();
		}		
	}
}
