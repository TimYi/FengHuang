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
			ResponseModel<RefundResponseData> result=service.sendKhthRequest(null, "20150722HZtm", "20150722", "100001042405", "983708160009501", null, 500.0, null, null);
			if(result.isSuccess()) {
				RefundResponseData data=result.getData();
				TestUtils.printEachField(data);
			} else {
				System.out.println(result.getErrorMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
