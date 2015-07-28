package com.fenghuangzhujia.eshop.admin.auth;

import com.fenghuangzhujia.eshop.admin.user.SystemUserDetails;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.security.rest.AuthenticationUtil;

public class SystemAuthUtil {

	public static SystemUserDetails getUserDetail() {
		try {
			return AuthenticationUtil.getUserDetail();
		} catch (Exception e) {
			throw new ErrorCodeException(SystemErrorCodes.UNAUTH,e);
		}
	}
}
