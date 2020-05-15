package com.yc.fresh.goods.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yc.fresh.goods.service.IGoodsInfoService;

@RestController
@RequestMapping("/goods")
public class GoodsInfoController {
	@Autowired
	private IGoodsInfoService goodsInfoService;
	
	/**
	 * 处理首页商品信息查询请求
	 * @return
	 */
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public Map<String, Object> finds(){
		return goodsInfoService.finds();
	}
}
