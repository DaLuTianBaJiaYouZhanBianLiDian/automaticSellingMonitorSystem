package com.zyuc.commodityInfoAction.model;

import com.zyuc.common.model.CommonContent;
import com.zyuc.common.model.condition.PagingCondition;
import com.zyuc.common.model.condition.SortDirectType;
import com.zyuc.common.model.condition.SortKeyType;

/**
 * 商品信息model
 * @author caihongwang
 * 
 */
public class CommodityInfoModel extends CommonContent {

	private Integer commodity_id;				//商品id
	private String commodity_code;				//商品编码
	private String commodity_name;				//商品名称
	private String commodity_type;				//商品种类
	private String commodity_unit_price;			//商品单价-->商品卖价
	private String commodity_vip_price;			//商品会员价
	private String commodity_image_path;			//商品图片地址
	private String commodity_quality_guarantee_period;	//商品保质期
	private String commodity_eche_one_total_num;	//商品每件总数-->一大件有多少瓶或者多少包
	
	//本地报价也属于网络报价的一种,在页面展示的时候，对商品报价等这三个信息，使用循环方式展示
	private String commodity_network_quotation;	//商品网络报价		格式:供应商名称1：价格1；供应商名称2：价格2；供应商名称3：价格3；
	private String commodity_network_url;		//商品网络url地址	格式:供应商名称1：URL1；供应商名称2：URL2；供应商名称3：URL3；
	private String commodity_network_supply;		//商品网络供应商	格式:供应商名称1；供应商名称2；供应商名称3；
	
	private String commodity_create_date;		//商品创建时间
	private String commodity_create_by;			//商品创建人
	private String commodity_update_date;		//商品更新时间
	private String commodity_update_by;			//商品更新人
	private String commodity_remark;				//商品备注说明
	private String commodity_delete_status = "0";		//商品删除状态		
	
	//用于分页
	private SortKeyType sortKeyType;
	private SortDirectType sortDirectType;
	private PagingCondition pagingCondition;
	
	public String toString() {
		 return   "商品id: " + commodity_id + 
			   " , 商品编码: " + commodity_code + 
			   " , 商品名称: " + commodity_name + 
			   " , 商品种类: " + commodity_type + 
			   " , 商品单价: " + commodity_unit_price + 
			   " , 商品会员价: " + commodity_vip_price + 
			   " , 商品图片地址: " + commodity_image_path + 
			   " , 商品保质期: " + commodity_quality_guarantee_period + 
			   " , 商品每件总数: " + commodity_eche_one_total_num + 
			   " , 商品网络报价: " + commodity_network_quotation + 
			   " , 商品网络url地址: " + commodity_network_url + 
			   " , 商品网络供应商: " + commodity_network_supply + 
			   " , 商品创建时间: " + commodity_create_date + 
			   " , 商品创建人: " + commodity_create_by + 
			   " , 商品更新时间: " + commodity_update_date + 
			   " , 商品更新人: " + commodity_update_by + 
			   " , 商品备注说明: " + commodity_remark + 
			   " , 商品删除状态: " + commodity_delete_status ;
	}
	
	public CommodityInfoModel() { 
		
	}

	public Integer getCommodity_id() {
		return commodity_id;
	}

	public void setCommodity_id(Integer commodity_id) {
		this.commodity_id = commodity_id;
	}

	public String getCommodity_code() {
		return commodity_code;
	}

	public void setCommodity_code(String commodity_code) {
		this.commodity_code = commodity_code;
	}

	public String getCommodity_name() {
		return commodity_name;
	}

	public void setCommodity_name(String commodity_name) {
		this.commodity_name = commodity_name;
	}

	public String getCommodity_type() {
		return commodity_type;
	}

	public void setCommodity_type(String commodity_type) {
		this.commodity_type = commodity_type;
	}

	public String getCommodity_unit_price() {
		return commodity_unit_price;
	}

	public void setCommodity_unit_price(String commodity_unit_price) {
		this.commodity_unit_price = commodity_unit_price;
	}

	public String getCommodity_vip_price() {
		return commodity_vip_price;
	}

	public void setCommodity_vip_price(String commodity_vip_price) {
		this.commodity_vip_price = commodity_vip_price;
	}

	public String getCommodity_image_path() {
		return commodity_image_path;
	}

	public void setCommodity_image_path(String commodity_image_path) {
		this.commodity_image_path = commodity_image_path;
	}

	public String getCommodity_eche_one_total_num() {
		return commodity_eche_one_total_num;
	}

	public void setCommodity_eche_one_total_num(String commodity_eche_one_total_num) {
		this.commodity_eche_one_total_num = commodity_eche_one_total_num;
	}

	public String getCommodity_network_quotation() {
		return commodity_network_quotation;
	}

	public void setCommodity_network_quotation(String commodity_network_quotation) {
		this.commodity_network_quotation = commodity_network_quotation;
	}

	public String getCommodity_network_url() {
		return commodity_network_url;
	}

	public void setCommodity_network_url(String commodity_network_url) {
		this.commodity_network_url = commodity_network_url;
	}

	public String getCommodity_network_supply() {
		return commodity_network_supply;
	}

	public void setCommodity_network_supply(String commodity_network_supply) {
		this.commodity_network_supply = commodity_network_supply;
	}

	public String getCommodity_remark() {
		return commodity_remark;
	}

	public void setCommodity_remark(String commodity_remark) {
		this.commodity_remark = commodity_remark;
	}

	public String getCommodity_quality_guarantee_period() {
		return commodity_quality_guarantee_period;
	}

	public void setCommodity_quality_guarantee_period(String commodity_quality_guarantee_period) {
		this.commodity_quality_guarantee_period = commodity_quality_guarantee_period;
	}

	public String getCommodity_create_date() {
		return commodity_create_date;
	}

	public void setCommodity_create_date(String commodity_create_date) {
		this.commodity_create_date = commodity_create_date;
	}

	public String getCommodity_create_by() {
		return commodity_create_by;
	}

	public void setCommodity_create_by(String commodity_create_by) {
		this.commodity_create_by = commodity_create_by;
	}

	public String getCommodity_update_date() {
		return commodity_update_date;
	}

	public void setCommodity_update_date(String commodity_update_date) {
		this.commodity_update_date = commodity_update_date;
	}

	public String getCommodity_update_by() {
		return commodity_update_by;
	}

	public void setCommodity_update_by(String commodity_update_by) {
		this.commodity_update_by = commodity_update_by;
	}

	public String getCommodity_delete_status() {
		return commodity_delete_status;
	}

	public void setCommodity_delete_status(String commodity_delete_status) {
		this.commodity_delete_status = commodity_delete_status;
	}

	public SortKeyType getSortKeyType() {
		return sortKeyType;
	}

	public void setSortKeyType(SortKeyType sortKeyType) {
		this.sortKeyType = sortKeyType;
	}

	public SortDirectType getSortDirectType() {
		return sortDirectType;
	}

	public void setSortDirectType(SortDirectType sortDirectType) {
		this.sortDirectType = sortDirectType;
	}

	public PagingCondition getPagingCondition() {
		return pagingCondition;
	}

	public void setPagingCondition(PagingCondition pagingCondition) {
		this.pagingCondition = pagingCondition;
	}
	
	
	

}
