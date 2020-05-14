package com.yc.fresh.user.mapper;

import com.yc.fresh.user.entity.MemberInfo;

public interface IMemberInfoMapper {
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
