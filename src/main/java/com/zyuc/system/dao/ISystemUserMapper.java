package com.zyuc.system.dao;

import java.util.List;
import java.util.Map;

import com.zyuc.common.model.SystemFunction;
import com.zyuc.system.model.SystemRole;
import com.zyuc.system.model.SystemUser;
import com.zyuc.system.model.condition.UserTableCondition;


public interface ISystemUserMapper {

	public List<SystemUser> getUsersByCondition(UserTableCondition condition);

	public int getUsersSizeByCondition(UserTableCondition condition);

	public int saveSystemUser(SystemUser user);

	public SystemUser getUserById(Map<String, Object> params);

	public boolean updateSystemUser(SystemUser user);

	public boolean delUserById(String userId);

	public List<SystemRole> getRolesByCondition(UserTableCondition condition);

	public int getRolesSizeByCondition(UserTableCondition condition);

	public boolean saveRole(SystemRole role);

	public SystemRole getRoleById(Map<String, Object> params);

	public boolean updateRole(SystemRole systemRole);

	public List<SystemFunction> getFunctionsByCondition(UserTableCondition condition);

	public int getFunctionsSizeByCondition(UserTableCondition condition);

	public boolean saveFunction(SystemFunction systemFunction);

	public SystemFunction getFunctionById(Map<String, Object> params);

	public boolean updateFunction(SystemFunction systemFunction);

	public SystemRole getRoleByUserId(Map<String, Object> params);

	public void deleteUserRoles(Map<String, Object> params);

	public void addUserRoles(SystemUser user);
	
	public List<SystemFunction> getFunctionsByRoleId(Map<String, Object> params);

	public void addRoleFunctions(Map<String, Object> params);

	public void deleteRoleFunctions(Map<String, Object> params);

	public SystemFunction getParentByFunctionId(Map<String, Object> params);

	public List<SystemFunction> getFunctionsByUserId(Map<String, Object> params);

	public List<String> getUsersByRoleId(Map<String, Object> params);

	public void deleteRoleFunctionsByFunctionId(Map<String, Object> params);

	
}