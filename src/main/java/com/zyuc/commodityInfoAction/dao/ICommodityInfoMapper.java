package com.zyuc.commodityInfoAction.dao;

import java.util.List;

import com.zyuc.commodityInfoAction.model.CommodityInfoModel;
import com.zyuc.system.model.condition.UserTableCondition;

/**
 * 商品信息Mapper
 * 主要是对商品信息商品信息的增删改查等对数据裤的操作
 * @author caihongwang
 *
 */
public interface ICommodityInfoMapper {

	/**
	 * 查询符合条件的商品信息
	 * @param condition
	 * @return
	 */
	public List<CommodityInfoModel> queryCommodityInfoList(CommodityInfoModel condition);
	
	/**
	 * 查询符合条件的商品信息的总数
	 * @param condition
	 * @return
	 */
	public int queryCommodityInfoTotal(CommodityInfoModel condition);
}
