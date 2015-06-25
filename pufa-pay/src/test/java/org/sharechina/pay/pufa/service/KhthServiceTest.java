package org.sharechina.pay.pufa.service;

import org.junit.Test;
import org.sharechina.pay.pufa.protocal.ResponseModel;
import org.sharechina.pay.pufa.protocal.refund.RefundResponseData;
import org.sharechina.pay.pufa.utils.TestUtils;

public class KhthServiceTest {

	@Test
	public void khth() {
		KhthService service=new KhthService();
		try {
			ResponseModel<RefundResponseData> result=service.sendKhthRequest(null, "test", "20151226", "12345678", "983708160009501", null, 888.0, null, null);
			TestUtils.printEachField(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
