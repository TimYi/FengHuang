package com.fenghuangzhujia.eshop.admin.user;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class SystemUserDetails implements UserDetails {
	
	private String id;
	private String username;
	private String password;
	private boolean verified;
	
	public SystemUserDetails(SystemUser user) {
		id=user.getId();
		username=user.getUsername();
		password=user.getPassword();
		verified=user.isVerified();
	}
	
	public String getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		return verified;
	}
	@Override
	public boolean isAccountNonLocked() {
		return verified;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return verified;
	}
	@Override
	public boolean isEnabled() {
		return verified;
	}
}
