package com.zyuc.commodityInfoAction.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyuc.commodityInfoAction.dao.ICommodityInfoMapper;
import com.zyuc.commodityInfoAction.model.CommodityInfoModel;
import com.zyuc.commodityInfoAction.service.ICommodityInfoService;
import com.zyuc.common.model.SystemFunction;
import com.zyuc.common.utils.GlobalConstant;
import com.zyuc.system.action.vo.TreeFunction;
import com.zyuc.system.dao.ISystemUserMapper;
import com.zyuc.system.model.SystemRole;
import com.zyuc.system.model.SystemUser;
import com.zyuc.system.model.condition.UserTableCondition;
import com.zyuc.system.service.ISystemUserService;
import com.zyuc.system.service.impl.SystemUserServiceImpl;

/**
 * 商品信息服务类
 * 主要是对商品信息的页面获取，商品信息的增删改查等业务操作
 * @author caihongwang
 *
 */
@Service
public class CommodityInfoService implements ICommodityInfoService{

	private static final Logger logger = LoggerFactory.getLogger(SystemUserServiceImpl.class);

	@Autowired
	private ICommodityInfoMapper commodityInfoMapper;
	
	/**
	 * 查询符合条件的商品信息
	 * @param condition
	 * @return
	 */
	@Override
	public List<CommodityInfoModel> queryCommodityInfoList(CommodityInfoModel condition) {
		List<CommodityInfoModel> CommodityInfos = null;
		try {
			condition.setDbsystemname(GlobalConstant.DB_SYSTEM);
			CommodityInfos = commodityInfoMapper.queryCommodityInfoList(condition);
		} catch (Exception e) {
			CommodityInfos = new ArrayList<CommodityInfoModel>();
			logger.warn(e.getMessage(), e);
		}
		return CommodityInfos;
	}

	/**
	 * 查询符合条件的商品信息的总数
	 * @param condition
	 * @return
	 */
	@Override
	public int queryCommodityInfoTotal(CommodityInfoModel condition) {
		int size = 0;
		try {
			condition.setDbsystemname(GlobalConstant.DB_SYSTEM);
			size = commodityInfoMapper.queryCommodityInfoTotal(condition);
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
		return size;
	}

	
}
