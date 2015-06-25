package org.sharechina.pay.pufa.protocal;


public class SimplePlainRequestData implements RequestData {

	@Override
	public TransName getTransName() {
		return TransName.KHCX;
	}
	
	@Override
	public PlainData getPlainData() {
		return new SimplePlainData();
	}
}
