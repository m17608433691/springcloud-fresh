package com.yc.fresh.user.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.fresh.user.util.SendMailUtil;
import com.yc.fresh.user.entity.MemberInfo;
import com.yc.fresh.user.service.IMemberInfoService;
import com.yc.fresh.user.util.ResponseUtil;

@RestController
@RequestMapping("/member")
public class MemberInfoController {
	@Autowired
	private SendMailUtil sendMailUtil;
	
	@Autowired
	private IMemberInfoService service;
	
	@RequestMapping("/code")
	public int code(String receive, String name, HttpSession session ){
		String code = "";
		Random rd = new Random();
		while(code.length() < 6) {
			code += rd.nextInt(10);
		}
		
		if(sendMailUtil.sendHtmlMail(receive, name, code)) {
			session.setAttribute("code", code);
			
			TimerTask task = new TimerTask() {
				
				@Override
				public void run() {
					session.removeAttribute("code");
				}
			};
			
			Timer timer = new Timer();
			timer.schedule(task, 180000); 	//3分钟执行任务一次
			
			return 1;
		}
		return 0;
	}
	
	/**
	 * 会员注册
	 * @param session
	 * @param mf
	 * @return
	 */
	@RequestMapping("/reg")
	public Map<String, Object> reg(HttpSession session, MemberInfo mf) {
		Object obj = session.getAttribute("code");
		if(obj == null) {	//说明验证码过期了
			return ResponseUtil.responseMap(501, null, null);
		}
		
		String code = (String) obj;
		if(!code.equals(mf.getRealName())) {	//验证码错误
			return ResponseUtil.responseMap(502, null, null);
		}
		
		int result = service.reg(mf);
		if(result > 0) {
			return ResponseUtil.responseMap(200, null, null);
		}
		return ResponseUtil.responseMap(503, null, null);
	}
	
	/**
	 * 会员登录
	 * @param session
	 * @param mf
	 * @return
	 */
	@RequestMapping("/login")
	public Map<String, Object> login(HttpSession session, MemberInfo mf) {
		String code = mf.getRealName();
		String vcode = (String) session.getAttribute("validateCode");
		
		if(!code.equalsIgnoreCase(vcode)) {
			return ResponseUtil.responseMap(501, null, null);
		}
		MemberInfo memberInfo = service.login(mf);
		
		if(memberInfo == null) {
			return ResponseUtil.responseMap(500, null, null);
		}
		
		session.setAttribute("loginMember", memberInfo);
		return ResponseUtil.responseMap(200, null, null);
	}
	
	/**
	 * 检查用户有没有登录
	 * @param session
	 * @return
	 */
	@RequestMapping("/check")
	public Map<String, Object> checkLogin(HttpSession session){
		Object obj = session.getAttribute("loginMember");
		if(obj == null) {
			return Collections.emptyMap();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member", obj);
		map.put("carts", session.getAttribute("cartInfos"));
		return map;
	}
}
