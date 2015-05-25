package com.fenghuangzhujia.eshop.core.authentication.authority;

import org.springframework.security.core.GrantedAuthority;

public class PermissionAuthority implements GrantedAuthority {

	private static final long serialVersionUID = -5814210050017123144L;
	
	private String authority;

	@Override
	public String getAuthority() {
		return authority;
	}

	public PermissionAuthority(AbstractAuthority permission) {
		this.authority=permission.getAuthority();
	}	
}
