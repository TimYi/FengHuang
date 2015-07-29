package org.sharechina.pay.pufa.service;

import java.security.SignatureException;

import org.junit.Test;
import org.sharechina.pay.pufa.protocal.ResponseModel;
import org.sharechina.pay.pufa.protocal.TransName;
import org.sharechina.pay.pufa.protocal.query.QueryResponseData;
import org.sharechina.pay.pufa.utils.TestUtils;

public class KhcxServiceTest {

	@Test
	public void khcx() {
		KhcxService service=new KhcxService();
		try {
			ResponseModel<QueryResponseData> result=service.sendKhcxRequest(/*"983708160009501"*/"360448160000101", TransName.KHZF, "201507223mwM");
			if(result.isSuccess()) {
				TestUtils.printEachField(result.getData());
			} else {
				System.out.println(result.getErrorMsg());
			}
		} catch (SignatureException e) {
			e.printStackTrace();
		}
	}
}
