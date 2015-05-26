package com.fenghuangzhujia.eshop.core.validate.core;

public interface CredentialCreater {
	Object createCredential(String id, String code, Integer expireMinutes);
}
