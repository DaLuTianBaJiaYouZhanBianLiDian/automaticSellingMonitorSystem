package com.zyuc.system.model;

import java.util.Date;

import com.zyuc.common.model.CommonContent;

public class SystemRole extends CommonContent{
	
	private Integer id;
	private String roleName;
	private String roleType;
	private String roleDesc;
	private String createUser;
	private String updateUser;
	private String isDelete;
	private Date createTime;
	private Date updateTime;
	private Integer isSelect;
	
	private String selectFuncrionIds;

	public String getSelectFuncrionIds() {
		return selectFuncrionIds;
	}

	public void setSelectFuncrionIds(String selectFuncrionIds) {
		this.selectFuncrionIds = selectFuncrionIds;
	}

	public Integer getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(Integer isSelect) {
		this.isSelect = isSelect;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "SystemRole [id=" + id + ", roleName=" + roleName
				+ ", roleType=" + roleType + ", roleDesc=" + roleDesc
				+ ", createUser=" + createUser + ", updateUser=" + updateUser 
				+ ", isDelete=" + isDelete + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}
