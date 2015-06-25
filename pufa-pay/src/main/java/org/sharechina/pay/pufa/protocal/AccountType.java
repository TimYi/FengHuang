package org.sharechina.pay.pufa.protocal;

public enum AccountType {

	JIEJJI("1"),
	XINYONG("U");
	private String value;
	public String getValue() {
		return value;
	}
	private AccountType(String value) {
		this.value=value;
	}
	@Override
	public String toString() {
		return getValue();
	}
}
