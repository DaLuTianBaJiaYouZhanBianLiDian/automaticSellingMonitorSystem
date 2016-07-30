package com.zyuc.system.service;

import java.util.List;
import java.util.Map;

import com.zyuc.common.model.SystemFunction;
import com.zyuc.system.model.SystemUser;

public interface ILoginService {
	
	/**
	 * 验证用户信息
	 * @param username
	 * @param password
	 * @return
	 */
	public SystemUser checkUser(String username, String password);

	/**
	 * 查询用户权限信息
	 * @param userId
	 * @return
	 */
	public List<SystemFunction> getPermissionFunctions(Integer userId);
}
