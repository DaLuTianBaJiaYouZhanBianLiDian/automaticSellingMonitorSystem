package com.zyuc.system.service.impl;

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

import com.zyuc.common.model.SystemFunction;
import com.zyuc.common.utils.GlobalConstant;
import com.zyuc.system.action.vo.TreeFunction;
import com.zyuc.system.dao.ISystemUserMapper;
import com.zyuc.system.model.SystemRole;
import com.zyuc.system.model.SystemUser;
import com.zyuc.system.model.condition.UserTableCondition;
import com.zyuc.system.service.ISystemUserService;

@Service
public class SystemUserServiceImpl implements ISystemUserService {
	private static final Logger logger = LoggerFactory.getLogger(SystemUserServiceImpl.class);

	@Autowired
	private ISystemUserMapper systemUserMapper;

	@Override
	public List<SystemUser> getUsersByCondition(UserTableCondition condition) {
		List<SystemUser> systemUsers = null;
		try {
			condition.setDbsystemname(GlobalConstant.DB_SYSTEM);
			systemUsers = systemUserMapper.getUsersByCondition(condition);
		} catch (Exception e) {
			systemUsers = new ArrayList<SystemUser>();
			logger.warn(e.getMessage(), e);
		}
		return systemUsers;
	}

	@Override
	public int getUsersSizeByCondition(UserTableCondition condition) {
		int size = 0;
		try {
			condition.setDbsystemname(GlobalConstant.DB_SYSTEM);
			size = systemUserMapper.getUsersSizeByCondition(condition);
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
		return size;
	}

	@Override
	public boolean saveSystemUser(SystemUser user) {
		boolean flag = false;
		try {
			user.setDbsystemname(GlobalConstant.DB_SYSTEM);
			systemUserMapper.saveSystemUser(user);
			systemUserMapper.addUserRoles(user);
			flag = true;
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
		return flag;
	}

	@Override
	public SystemUser getUserById(String userId) {
		SystemUser systemUser = null;
		if (StringUtils.isBlank(userId)) {
			return systemUser;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dbsystemname", GlobalConstant.DB_SYSTEM);
		params.put("userId", userId);
		systemUser = systemUserMapper.getUserById(params);
		
		UserTableCondition condition = new UserTableCondition();
		condition.setDbsystemname(GlobalConstant.DB_SYSTEM);
		List<SystemRole> allRoles = systemUserMapper.getRolesByCondition(condition);
		if (systemUser != null) {
			SystemRole selectRole = systemUserMapper.getRoleByUserId(params);
			for (SystemRole role : allRoles) {
				if (selectRole != null && role.getId() == selectRole.getId()) {
					role.setIsSelect(1);
				} else {
					role.setIsSelect(0);
				}
			}
		} else {
			systemUser = new SystemUser();
		}
		if (allRoles != null && allRoles.size() > 0) {
			systemUser.setRoles(allRoles);
		}
		return systemUser;
	}

	@Override
	public SystemUser updateSystemUser(SystemUser user) {
		try {
			user.setDbsystemname(GlobalConstant.DB_SYSTEM);
			systemUserMapper.updateSystemUser(user);
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("dbsystemname", GlobalConstant.DB_SYSTEM);
			params.put("userId", user.getUserId());
			systemUserMapper.deleteUserRoles(params);
			systemUserMapper.addUserRoles(user);
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
		return user;
	}

	@Override
	public boolean deleteUserById(String userId) {
		if (StringUtils.isBlank(userId)) {
			return false;
		}
		try {
			SystemUser user = new SystemUser();
			user.setUserId(Integer.valueOf(userId));
			user.setIsDelete("0");
			user.setDbsystemname(GlobalConstant.DB_SYSTEM);
			systemUserMapper.updateSystemUser(user);
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("dbsystemname", GlobalConstant.DB_SYSTEM);
			params.put("userId", user.getUserId());
			systemUserMapper.deleteUserRoles(params);
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
			return false;
		}
		return true;
	}

	@Override
	public List<SystemRole> getRolesByCondition(UserTableCondition condition) {
		List<SystemRole> systemRoles = null;
		try {
			condition.setDbsystemname(GlobalConstant.DB_SYSTEM);
			systemRoles = systemUserMapper.getRolesByCondition(condition);
		} catch (Exception e) {
			systemRoles = new ArrayList<SystemRole>();
			logger.warn(e.getMessage(), e);
		}
		return systemRoles;
	}

	@Override
	public List<SystemRole> getAllRoles() {
		UserTableCondition condition = new UserTableCondition();
		condition.setDbsystemname(GlobalConstant.DB_SYSTEM);
		return systemUserMapper.getRolesByCondition(condition);
	}

	@Override
	public int getRolesSizeByCondition(UserTableCondition condition) {
		int size = 0;
		try {
			condition.setDbsystemname(GlobalConstant.DB_SYSTEM);
			size = systemUserMapper.getRolesSizeByCondition(condition);
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
		return size;
	}

	@Override
	public boolean saveRole(SystemRole systemRole) {
		try {
			systemRole.setDbsystemname(GlobalConstant.DB_SYSTEM);
			systemUserMapper.saveRole(systemRole);
			updateRoleFunctions(systemRole);
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
			return false;
		}
		return true;
	}

	private void updateRoleFunctions(SystemRole systemRole) {
		if (!"".equals(systemRole.getSelectFuncrionIds())) {
			List<String> allSelectIds = getAllSelectIds(systemRole
					.getSelectFuncrionIds());
			for (String functionId : allSelectIds) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("dbsystemname", GlobalConstant.DB_SYSTEM);
				params.put("roleId", systemRole.getId());
				params.put("functionId", functionId);
				systemUserMapper.addRoleFunctions(params);
			}
		}
	}

	@Override
	public SystemRole getRoleById(String roleId) {
		SystemRole systemRole = null;
		if (StringUtils.isBlank(roleId)) {
			return systemRole;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dbsystemname", GlobalConstant.DB_SYSTEM);
		params.put("roleId", roleId);
		return systemUserMapper.getRoleById(params);
	}

	@Override
	public SystemRole updateRole(SystemRole systemRole) {
		try {
			systemRole.setDbsystemname(GlobalConstant.DB_SYSTEM);
			systemUserMapper.updateRole(systemRole);
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("dbsystemname", GlobalConstant.DB_SYSTEM);
			params.put("roleId", systemRole.getId());
			systemUserMapper.deleteRoleFunctions(params);
			updateRoleFunctions(systemRole);
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
		return systemRole;
	}

	@Override
	public boolean deleteRoleById(String roleId) {
		if (StringUtils.isBlank(roleId)) {
			return false;
		}
		try {
			SystemRole systemRole = new SystemRole();
			systemRole.setId(Integer.valueOf(roleId));
			systemRole.setIsDelete("0");
			systemRole.setDbsystemname(GlobalConstant.DB_SYSTEM);
			systemUserMapper.updateRole(systemRole);
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("dbsystemname", GlobalConstant.DB_SYSTEM);
			params.put("roleId", systemRole.getId());
			systemUserMapper.deleteRoleFunctions(params);
			List<String> userIds = systemUserMapper.getUsersByRoleId(params);
			for (String userId : userIds) {
				if (!StringUtils.isEmpty(userId)) {
					params.put("userId", userId);
					systemUserMapper.deleteUserRoles(params);
				}
			}
		} catch (NumberFormatException e) {
			logger.warn(e.getMessage(), e);
			return false;
		}
		return true;
	}

	@Override
	public List<SystemFunction> getFunctionsByCondition(UserTableCondition condition) {
		List<SystemFunction> systemFunctions = null;
		try {
			condition.setDbsystemname(GlobalConstant.DB_SYSTEM);
			systemFunctions = systemUserMapper.getFunctionsByCondition(condition);
			for (SystemFunction function : systemFunctions) {
				fillingParentFunctions(function, systemFunctions);
			}
		} catch (Exception e) {
			systemFunctions = new ArrayList<SystemFunction>();
			logger.warn(e.getMessage(), e);
		}
		return systemFunctions;
	}

	private void fillingParentFunctions(SystemFunction function, List<SystemFunction> allFunctions) {
		if (function != null) {
			if (function.getSuper_id() != null) {
				if(allFunctions.size() > 0 ){
					for(SystemFunction f : allFunctions){
						if(f.getId() == function.getSuper_id()){
							function.setParentFunctionName(f.getFunction_name());
							
						}
					}
				}else {
					SystemFunction pFunction = getFunctionById(function.getSuper_id()+"");
					if (pFunction != null
							&& !StringUtils.isEmpty(pFunction.getFunction_name())) {
						function.setParentFunctionName(pFunction.getFunction_name());
					}
				}
			} else {
				function.setParentFunctionName("");
			}
		}
	}

	@Override
	public int getFunctionsSizeByCondition(UserTableCondition condition) {
		int size = 0;
		try {
			condition.setDbsystemname(GlobalConstant.DB_SYSTEM);
			size = systemUserMapper.getFunctionsSizeByCondition(condition);
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
		return size;
	}

	@Override
	public boolean saveFucntion(SystemFunction systemFunction) {
		if(Integer.parseInt(systemFunction.getFunction_type()) == 1){
			systemFunction.setSuper_id(0);
		}
		systemFunction.setDbsystemname(GlobalConstant.DB_SYSTEM);
		return systemUserMapper.saveFunction(systemFunction);
	}

	@Override
	public SystemFunction getFunctionById(String functionId) {
		SystemFunction systemFunction = null;
		try {
			if (StringUtils.isBlank(functionId)) {
				return systemFunction;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("dbsystemname", GlobalConstant.DB_SYSTEM);
			params.put("functionId", functionId);
			systemFunction = systemUserMapper.getFunctionById(params);
			fillingParentFunctions(systemFunction, new ArrayList<SystemFunction>());
		} catch (Exception e) {
			systemFunction = new SystemFunction();
			logger.warn(e.getMessage(), e);
		}
		return systemFunction;

	}

	@Override
	public boolean updateFunction(SystemFunction systemFunction) {
		systemFunction.setIsDelete("1");
		systemFunction.setDbsystemname(GlobalConstant.DB_SYSTEM);
		return systemUserMapper.updateFunction(systemFunction);
	}

	@Override
	public boolean deleteFunctionById(String functionId) {
		try {
			if (StringUtils.isBlank(functionId)) {
				return false;
			}
			SystemFunction systemFunction = new SystemFunction();
			systemFunction.setId(Integer.valueOf(functionId));
			systemFunction.setIsDelete("0");
			systemFunction.setUpdate_time(new Date());
			systemFunction.setDbsystemname(GlobalConstant.DB_SYSTEM);
			systemUserMapper.updateFunction(systemFunction);
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("dbsystemname", GlobalConstant.DB_SYSTEM);
			params.put("functionId", functionId);
			systemUserMapper.deleteRoleFunctionsByFunctionId(params);
		} catch (NumberFormatException e) {
			logger.warn(e.getMessage(), e);
			return false;
		}
		return true;
	}

	@Override
	public List<TreeFunction> getTreeFunctions4Role(String roleId) {
		List<TreeFunction> treeFunctions = null;
		try {
			// 得到所有
			List<SystemFunction> allFunctions = getFunctionsByCondition(new UserTableCondition());
			System.out.println("db : " + new Date());
			List<SystemFunction> functions = formatePermissionFunctions(allFunctions);
			showOrderFunctions(functions);
			// 有roleId 获取用户权限 没有直接返回
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("dbsystemname", GlobalConstant.DB_SYSTEM);
			params.put("roleId", roleId);
			List<SystemFunction> userFunctions = systemUserMapper.getFunctionsByRoleId(params);
			List<Object> selectIdList = new ArrayList<Object>();
			for (SystemFunction function : userFunctions) {
				selectIdList.add(function.getId());
			}
			// 组装 ztree
			treeFunctions = new ArrayList<TreeFunction>();
			getTree4Role(functions, selectIdList, treeFunctions);
		} catch (Exception e) {
			treeFunctions = new ArrayList<TreeFunction>();
			logger.warn(e.getMessage(), e);
		}
		return treeFunctions;
	}
	
	@Override
	public List<TreeFunction> getTreeFunctions4Menu(String functionId) {
		List<TreeFunction> treeFunctions;
		try {
			// 得到所有
			List<SystemFunction> allFunctions = getFunctionsByCondition(new UserTableCondition());
			System.out.println("db : " + new Date());
			List<SystemFunction> functions = formatePermissionFunctions(allFunctions);
			showOrderFunctions(functions);
			// 有functionId 得到父id
			String super_id = "";
			if (!StringUtils.isEmpty(functionId)) {
				SystemFunction selectFunction = getFunctionById(functionId);
				super_id = selectFunction.getSuper_id() + "";
			}
			// 组装 ztree
			treeFunctions = new ArrayList<TreeFunction>();
			getTree4Menu(functions, super_id, treeFunctions);
		} catch (Exception e) {
			treeFunctions = new ArrayList<TreeFunction>();
			logger.warn(e.getMessage(), e);
		}
		return treeFunctions;
	}

	@Override
	public SystemFunction getParentByFunctionId(String functionId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dbsystemname", GlobalConstant.DB_SYSTEM);
		params.put("functionId", functionId);
		SystemFunction pFunction =  systemUserMapper.getParentByFunctionId(params);
		return pFunction;
	}
	@Override
	public List<SystemFunction> getFunctionsByUserId(Integer userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dbsystemname", GlobalConstant.DB_SYSTEM);
		params.put("userId", userId);
		return systemUserMapper.getFunctionsByUserId(params);
	}

	@Override
	public List<String> getUsersByRoleId(String roleId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dbsystemname", GlobalConstant.DB_SYSTEM);
		params.put("roleId", roleId);
		return systemUserMapper.getUsersByRoleId(params);
	}

	private void getTree4Menu(List<SystemFunction> functions, String super_id,
			List<TreeFunction> treeFunctions) {
		for (SystemFunction function : functions) {
			if(!"3".equals(function.getFunction_type())){
				TreeFunction tree = new TreeFunction();
				tree.setId(function.getId());
				tree.setpId(function.getSuper_id());
				tree.setName(function.getFunction_name());
				if (Integer.parseInt(function.getFunction_type()) == 1) {
					tree.setOpen(true);
				}
				if (!StringUtils.isEmpty(super_id)
						&& super_id.equals(function.getId() + "")) {
					tree.setChecked(true);
				}
				if (function.getChildFunctions().size() > 0) {
					getTree4Menu(function.getChildFunctions(), super_id,
							treeFunctions);
				}
				treeFunctions.add(tree);
			}
		}
	}

	private void getTree4Role(List<SystemFunction> functions,
			List<Object> selectIdList, List<TreeFunction> treeFunctions) {
		for (SystemFunction function : functions) {
			TreeFunction tree = new TreeFunction();
			tree.setId(function.getId());
			tree.setpId(function.getSuper_id());
			tree.setName(function.getFunction_name());
			if (Integer.parseInt(function.getFunction_type()) == 1) {
				tree.setOpen(true);
			}
			if (selectIdList.contains(function.getId())) {
				tree.setChecked(true);
			}
			if (function.getChildFunctions().size() > 0) {
				getTree4Role(function.getChildFunctions(), selectIdList,
						treeFunctions);
			}
			treeFunctions.add(tree);
		}
	}

	private List<SystemFunction> formatePermissionFunctions(
			List<SystemFunction> dbFunctions) {
		for (SystemFunction function : dbFunctions) {
			int functionId = function.getId();
			List<SystemFunction> childFunctions = new ArrayList<SystemFunction>();
			for (SystemFunction cf : dbFunctions) {
				if (cf.getSuper_id() == functionId) {
					childFunctions.add(cf);
				}
			}
			function.setChildFunctions(childFunctions);
		}
		List<SystemFunction> permissionFunctions = new ArrayList<SystemFunction>();
		return getPermissionFunctions(permissionFunctions, dbFunctions, 1);
	}
	
	private List<SystemFunction> getPermissionFunctions(
			List<SystemFunction> permissionFunctions,
			List<SystemFunction> functions, int type) {
		for (SystemFunction function : functions) {
			if (type == Integer.parseInt(function.getFunction_type())) {
				permissionFunctions.add(function);
			}
		}
		return permissionFunctions;
	}
	private void showOrderFunctions(List<SystemFunction> permissionFunctions) {
		sortCollections(permissionFunctions);
		for(SystemFunction function : permissionFunctions){
			if(function.getChildFunctions().size()>0){
				showOrderFunctions(function.getChildFunctions());
			}
		}
		
	}
	private void sortCollections(List<SystemFunction> functions) {
		Collections.sort(functions, new Comparator<SystemFunction>(){  
			@Override
			public int compare(SystemFunction f1, SystemFunction f2) {
				return f1.compareTo(f2);
			}  
        });
	}
	

	private List<String> getAllSelectIds(String selectFuncrionIds) {
		String[] selectIds = selectFuncrionIds.split(",");
		List<String> allIds = new ArrayList<String>();
		for (String functionId : selectIds) {
			fillingAllSelectIds(allIds, functionId);
		}
		return allIds;
	}

	private void fillingAllSelectIds(List<String> allIds, String functionId) {
		allIds.add(functionId);
		SystemFunction pFunction = getParentByFunctionId(functionId);
		if(pFunction != null){
			String id = pFunction.getId() +"";
			if ("0".equals(id ) || allIds.contains(id)) {
				return;
			} else {
				fillingAllSelectIds(allIds, id);
			}
		}
	}
}
