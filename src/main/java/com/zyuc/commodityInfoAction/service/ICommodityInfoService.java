package com.zyuc.commodityInfoAction.service;

import java.util.List;

import com.zyuc.commodityInfoAction.model.CommodityInfoModel;
import com.zyuc.system.model.SystemUser;
import com.zyuc.system.model.condition.UserTableCondition;

/**
 * 商品信息服务接口ß
 * 主要是对商品信息的页面获取，商品信息的增删改查等业务操作
 * @author caihongwang
 *
 */
public interface ICommodityInfoService {

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
