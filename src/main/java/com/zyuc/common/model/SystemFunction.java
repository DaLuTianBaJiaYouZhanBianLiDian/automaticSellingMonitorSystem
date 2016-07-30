package com.zyuc.common.model;

import java.util.Date;
import java.util.List;

public class SystemFunction extends CommonContent implements Comparable<SystemFunction>{
	
	private Integer id;
	private Integer super_id;
	private String function_name;
	private String function_url;
	private String function_type;
	private String function_icon;
	private String function_desc;
	private String create_user;
	private String update_user;
	private Date create_time;
	private Date update_time;
	private String isDelete;
	private Integer showOrder;
	private List<SystemFunction> childFunctions;
	private String parentFunctionName;
	private String function_path_names;
	private String function_path_ids;
	private SystemFunction parentFunction;
	
	public SystemFunction getParentFunction() {
		return parentFunction;
	}
	public void setParentFunction(SystemFunction parentFunction) {
		this.parentFunction = parentFunction;
	}
	public String getFunction_path_names() {
		return function_path_names;
	}
	public void setFunction_path_names(String function_path_names) {
		this.function_path_names = function_path_names;
	}
	public String getFunction_path_ids() {
		return function_path_ids;
	}
	public void setFunction_path_ids(String function_path_ids) {
		this.function_path_ids = function_path_ids;
	}
	public String getParentFunctionName() {
		return parentFunctionName;
	}
	public void setParentFunctionName(String parentFunctionName) {
		this.parentFunctionName = parentFunctionName;
	}
	public String getCreate_user() {
		return create_user;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	public String getUpdate_user() {
		return update_user;
	}
	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSuper_id() {
		return super_id;
	}
	public void setSuper_id(Integer super_id) {
		this.super_id = super_id;
	}
	public String getFunction_name() {
		return function_name;
	}
	public void setFunction_name(String function_name) {
		this.function_name = function_name;
	}
	public String getFunction_url() {
		return function_url;
	}
	public void setFunction_url(String function_url) {
		this.function_url = function_url;
	}
	public String getFunction_type() {
		return function_type;
	}
	public void setFunction_type(String function_type) {
		this.function_type = function_type;
	}
	public String getFunction_icon() {
		return function_icon;
	}
	public void setFunction_icon(String function_icon) {
		this.function_icon = function_icon;
	}
	public String getFunction_desc() {
		return function_desc;
	}
	public void setFunction_desc(String function_desc) {
		this.function_desc = function_desc;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public List<SystemFunction> getChildFunctions() {
		return childFunctions;
	}
	public void setChildFunctions(List<SystemFunction> childFunctions) {
		this.childFunctions = childFunctions;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	@Override
	public int compareTo(SystemFunction function) {
		if(this == function){
            return 0;            
        }else if (function!=null && function instanceof SystemFunction) {   
            if(showOrder<=function.getShowOrder()){
                return -1;
            }else{
	            return 1;
	        }
	    }else{
	        return -1;
	    }
	}
	 
}
