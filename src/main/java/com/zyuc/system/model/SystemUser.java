package com.zyuc.system.model;

import java.util.Date;
import java.util.List;

import com.zyuc.common.model.CommonContent;

public class SystemUser extends CommonContent{
	private Integer userId;
	private String userName;
	private String password;
	private String userType;
	private String email;
	private String phone;
	private String mobile;
	private String status;
	private String qq;
	private String weixin;
	private Date dateTime;
	private Date updateTime;
	private String isDelete;
	private Integer selectRoleId;
	private List<SystemRole> roles; 
	 
	public Integer getSelectRoleId() {
		return selectRoleId;
	}
	public void setSelectRoleId(Integer selectRoleId) {
		this.selectRoleId = selectRoleId;
	}
	public List<SystemRole> getRoles() {
		return roles;
	}
	public void setRoles(List<SystemRole> roles) {
		this.roles = roles;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "SystemUser [userId=" + userId + ", userName=" + userName + ", password=" + password + ", userType="
				+ userType + ", email=" + email + ", phone=" + phone + ", mobile=" + mobile + ", dateTime=" + dateTime
				+ ", updateTime=" + updateTime + "]";
	}
	
	
}
