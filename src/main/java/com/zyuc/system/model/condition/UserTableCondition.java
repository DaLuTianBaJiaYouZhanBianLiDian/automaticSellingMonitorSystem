package com.zyuc.system.model.condition;

import com.zyuc.common.model.CommonContent;
import com.zyuc.common.model.condition.PagingCondition;
import com.zyuc.common.model.condition.SortDirectType;
import com.zyuc.common.model.condition.SortKeyType;

public class UserTableCondition extends CommonContent{

	private SortKeyType sortKeyType;
	private SortDirectType sortDirectType;
	private PagingCondition pagingCondition;
	private String searchValue;
	private String userId;
	private String userName;
	private String userType;
	private String status;
	
	private String roleName;
	private String roleType;
	
	private String function_name;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFunction_name() {
		return function_name;
	}
	public void setFunction_name(String function_name) {
		this.function_name = function_name;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
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
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public PagingCondition getPagingCondition() {
		return pagingCondition;
	}
	public void setPagingCondition(PagingCondition pagingCondition) {
		this.pagingCondition = pagingCondition;
	}
	
}
