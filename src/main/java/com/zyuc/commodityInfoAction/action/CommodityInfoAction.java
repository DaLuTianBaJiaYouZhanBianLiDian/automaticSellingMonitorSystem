package com.zyuc.commodityInfoAction.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyuc.commodityInfoAction.model.CommodityInfoModel;
import com.zyuc.commodityInfoAction.service.ICommodityInfoService;
import com.zyuc.common.model.SystemFunction;
import com.zyuc.common.model.condition.PageInfo;
import com.zyuc.common.utils.ParseJackson;
import com.zyuc.system.action.SystemUserAction;
import com.zyuc.system.model.SystemUser;
import com.zyuc.system.model.condition.UserTableCondition;
import com.zyuc.system.service.ISystemUserService;

/**
 * 商品信息控制类
 * 主要是对商品信息的页面获取，商品信息的增删改查等操作
 * @author caihongwang
 *
 */
@Controller
public class CommodityInfoAction {

	private static final Logger logger = LoggerFactory.getLogger(CommodityInfoAction.class);

	//商品信息服务类
	@Autowired
	private ICommodityInfoService commodityInfoService;
	
	/**
	 * 获取商品首页
	 * @param model
	 * @return
	 */
	@RequestMapping("/commodityInfo/commodityInfoIndex")
	public String commodityInfoIndex(ModelMap model) {
		return "/automaticSellingMonitorSystem/page/commodityInfo/commodityInfoIndex";
	}
	
	/**
	 * 查询符合条件下的商品信息
	 * @param conditionStr
	 * @return
	 */
	@RequestMapping("/commodityInfo/queryCommodityInfoList")
	public @ResponseBody
	Map<String, Object> queryCommodityInfoList(String conditionStr) {
		CommodityInfoModel condition = ParseJackson.parseStringToObject(
				conditionStr, CommodityInfoModel.class);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<CommodityInfoModel> datas = commodityInfoService
					.queryCommodityInfoList(condition);
			PageInfo pageInfo = new PageInfo();
			int size = commodityInfoService.queryCommodityInfoTotal(condition);
			pageInfo.setCountTotal(size);
			resultMap.put("pageInfo", pageInfo);
			if (datas.size() > 0) {
				resultMap.put("flag", "empty");
			} else {
				resultMap.put("flag", "data");
			}
			resultMap.put("datas", datas);
			resultMap.put("status", "succeed");
		} catch (Exception e) {
			resultMap.put("status", "failure");
			logger.warn(e.getMessage(), e);
		}
		return resultMap;
	}
	
	/**
	 * 增加和修改商品的信息
	 * @param model
	 * @param functionId
	 * @return
	 */
	@RequestMapping("/commodityInfo/addAndEditCommodityInfo")
	public String addAndEditCommodityInfo(ModelMap model) {
		return "/automaticSellingMonitorSystem/page/commodityInfo/addAndEditCommodityInfo";
	}
}
