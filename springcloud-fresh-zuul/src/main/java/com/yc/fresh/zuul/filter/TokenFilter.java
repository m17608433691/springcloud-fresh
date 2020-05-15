package com.yc.fresh.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

public class TokenFilter extends ZuulFilter{

	@Override
	public String filterType() {
		return "pre";
	}
	
	/**
	 * 自定义顺序
	 */
	@Override
	public int filterOrder() {
		return 0;
	}
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		return null;
	}
}
