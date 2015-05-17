package com.fenghuangzhujia.eshop.core.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

/**
 * 用于禁用session验证登录
 * @author pc
 *
 */
@Component(value="nullSecurityContextRepository")
public class NullSecurityContextRepository implements SecurityContextRepository {

	@Override
	public SecurityContext loadContext(
			HttpRequestResponseHolder requestResponseHolder) {
		SecurityContext context=new SecurityContextImpl();
		return context;
	}

	@Override
	public void saveContext(SecurityContext context,
			HttpServletRequest request, HttpServletResponse response) {
	}

	@Override
	public boolean containsContext(HttpServletRequest request) {
		return false;
	}
}
