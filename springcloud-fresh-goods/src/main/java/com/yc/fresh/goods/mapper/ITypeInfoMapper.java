package com.yc.fresh.goods.mapper;

import java.util.List;

import com.yc.fresh.goods.entity.TypeInfo;

public interface ITypeInfoMapper {
	/**
	 * 查询所有商品类型信息
	 * @return
	 */
	public List<TypeInfo> finds();
}
