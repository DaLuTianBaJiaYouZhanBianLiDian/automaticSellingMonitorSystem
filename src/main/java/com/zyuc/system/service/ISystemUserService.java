package com.zyuc.system.service;

import java.util.List;

import com.zyuc.common.model.SystemFunction;
import com.zyuc.system.action.vo.TreeFunction;
import com.zyuc.system.model.SystemRole;
import com.zyuc.system.model.SystemUser;
import com.zyuc.system.model.condition.UserTableCondition;


public interface ISystemUserService {

	/**
	 * 查询所有用户
	 * @param condition
	 * @return
	 */
	public List<SystemUser> getUsersByCondition(UserTableCondition condition);

	/**
	 * 查询用户数量
	 * @param condition
	 * @return
	 */
	public int getUsersSizeByCondition(UserTableCondition condition);

	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	public boolean saveSystemUser(SystemUser user);

	/**
	 * 根据用户ID查询用户
	 * @param userid
	 * @return
	 */
	public SystemUser getUserById(String userId);

	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	public SystemUser updateSystemUser(SystemUser user);

	/**
	 * 根据用户ID删除用户
	 * @param userid
	 * @return
	 */

	/**
	 * 查询所有角色
	 * @param condition
	 * @return
	 */
	public List<SystemRole> getRolesByCondition(UserTableCondition condition);

	/**
	 * 查询角色数量
	 * @param condition
	 * @return
	 */
	public int getRolesSizeByCondition(UserTableCondition condition);

	/**
	 * 新增角色
	 * @param systemRole
	 * @return
	 */
	public boolean saveRole(SystemRole systemRole);

	/**
	 * 根据角色id查询角色
	 * @param roleId
	 * @return
	 */
	public SystemRole getRoleById(String roleId);

	/**
	 * 更新角色
	 * @param systemRole
	 * @return
	 */
	public SystemRole updateRole(SystemRole systemRole);

	/**
	 * 根据角色id删除角色
	 * @param roleId
	 * @return
	 */
	public boolean deleteRoleById(String roleId);

	/**
	 * 按条件查询菜单
	 * @param condition
	 * @return
	 */
	public List<SystemFunction> getFunctionsByCondition(UserTableCondition condition);

	/**
	 * 按条件查询菜单数量
	 * @param condition
	 * @return
	 */
	public int getFunctionsSizeByCondition(UserTableCondition condition);

	/**
	 * 新增菜单
	 * @param systemFunction
	 * @return
	 */
	public boolean saveFucntion(SystemFunction systemFunction);

	/**
	 * 根据菜单id查询菜单
	 * @param functionId
	 * @return
	 */
	public SystemFunction getFunctionById(String functionId);

	/**
	 * 更新菜单
	 * @param systemFunction
	 * @return
	 */
	public boolean updateFunction(SystemFunction systemFunction);

	/**
	 * 根据菜单id删除菜单
	 * @param functionId
	 * @return
	 */
	public boolean deleteFunctionById(String functionId);

	/**
	 * 得到所有角色
	 * @return
	 */
	public List<SystemRole> getAllRoles();

	/**
	 * 得到角色编辑中需要的 菜单组成的树
	 * @param roleId
	 * @return
	 */
	public List<TreeFunction> getTreeFunctions4Role(String roleId);

	/**
	 * 根据角色id查询菜单
	 * @param userId
	 * @return
	 */
	public List<SystemFunction> getFunctionsByUserId(Integer userId);

	/**
	 * 根据角色id查询菜单
	 * @param roleId
	 * @return
	 */
	public List<String> getUsersByRoleId(String roleId);

	/**
	 * 得到菜单编辑中 菜单 一级二级菜单组成的树
	 * @param functionId
	 * @return
	 */
	public List<TreeFunction> getTreeFunctions4Menu(String functionId);

	/**
	 * 根据用户id删除用户
	 * @param userId
	 * @return
	 */
	boolean deleteUserById(String userId);

	/**
	 * 根据菜单id得到父菜单
	 * @param functionId
	 * @return
	 */
	SystemFunction getParentByFunctionId(String functionId);

}
