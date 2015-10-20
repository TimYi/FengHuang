package com.fenghuangzhujia.eshop.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CorsFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//设置允许的请求来源，*允许任何来源的跨域请求，可以指定http://xxx.xxx.xxx形式的请求来源。
		response.setHeader("Access-Control-Allow-Origin", "*");
		//设置允许的请求方法
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		//跨域请求最长响应时间
		response.setHeader("Access-Control-Max-Age", "3600");
		//允许的跨域请求头
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with, "
				+ "Content-Type, fhzj_auth_token");
		filterChain.doFilter(request, response);		
	}
}
