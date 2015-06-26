package org.sharechina.pay.pufa.protocal;

public enum PayType {

	BUY("1"),
	DONATE("4"),
	KAQUAN("47");
	private String value;
	public String getValue() {
		return value;
	}
	private PayType(String value) {
		this.value=value;
	}
	@Override
	public String toString() {
		return getValue();
	}
}
