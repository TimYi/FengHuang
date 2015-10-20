package org.sharechina.pay.pufa.service;

import java.security.SignatureException;

import org.junit.Test;
import org.sharechina.pay.pufa.protocal.download.KhxzRequestData.SetFType;

public class KhxzServiceTest {

	@Test
	public void khxz() throws SignatureException {
		KhxzService khxzService=new KhxzService();
		String result=khxzService.khxz("360448160000301", "20150908", SetFType.ALL);
		//System.out.println(result);
	}
}
