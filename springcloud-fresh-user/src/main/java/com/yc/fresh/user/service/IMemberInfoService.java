package com.yc.fresh.user.service;

import com.yc.fresh.user.entity.MemberInfo;

public interface IMemberInfoService {
	/**
	 * 会员登录
	 * @param mf
	 * @return
	 */
	public MemberInfo login(MemberInfo mf);
	
	/**
	 * 会员注册
	 * @param mf
	 * @return
	 */
	public int reg(MemberInfo mf);
}
