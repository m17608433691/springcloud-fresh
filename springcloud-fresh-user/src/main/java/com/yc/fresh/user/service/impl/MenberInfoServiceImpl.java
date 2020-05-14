package com.yc.fresh.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.fresh.user.entity.MemberInfo;
import com.yc.fresh.user.mapper.IMemberInfoMapper;
import com.yc.fresh.user.service.IMemberInfoService;
import com.yc.fresh.user.util.StringUtil;

@Service
public class MenberInfoServiceImpl implements IMemberInfoService{
	@Autowired
	private IMemberInfoMapper mapper;
	
	/**
	 * 后台管理员登录
	 */
	@Override
	public MemberInfo login(MemberInfo mf) {
		if(StringUtil.checkNull(mf.getNickName(), mf.getPwd())) {
			return null;
		}
		return mapper.login(mf);
	}

	@Override
	public int reg(MemberInfo mf) {
		if(StringUtil.checkNull(mf.getNickName(), mf.getEmail(), mf.getPwd())) {
			return -1;
		}
		return mapper.reg(mf);
	}

}
