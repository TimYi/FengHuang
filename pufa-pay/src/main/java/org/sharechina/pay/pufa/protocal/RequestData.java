package org.sharechina.pay.pufa.protocal;

import org.dozer.DozerBeanMapper;

public interface RequestData {
	
	public static final DozerBeanMapper dozer=new DozerBeanMapper();

	TransName getTransName();
	
	PlainData getPlainData();
}
