package com.zyuc.system.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyuc.common.model.SystemFunction;
import com.zyuc.common.model.condition.PageInfo;
import com.zyuc.common.utils.GlobalConstant;
import com.zyuc.common.utils.ParseJackson;
import com.zyuc.system.action.vo.TreeFunction;
import com.zyuc.system.model.SystemRole;
import com.zyuc.system.model.SystemUser;
import com.zyuc.system.model.condition.UserTableCondition;
import com.zyuc.system.service.ILoginService;
import com.zyuc.system.service.ISystemUserService;

@Controller
public class SystemUserAction {

	private static final Logger logger = LoggerFactory.getLogger(SystemUserAction.class);

	@Autowired
	private ISystemUserService systemUserService;

	@Autowired
	private ILoginService loginService;

	@RequestMapping("/systemuser/userlist")
	public String list(ModelMap model) {
		List<SystemRole> roles = systemUserService.getAllRoles();
		model.put("roles", roles);
		return "/automaticSellingMonitorSystem/page/system/systemuser";
	}

	@RequestMapping("/systemuser/rolelist")
	public String rolelist(ModelMap model) {
		return "/automaticSellingMonitorSystem/page/system/systemrole";
	}

	@RequestMapping("/systemuser/functionlist")
	public String functionlist(ModelMap model) {
		return "/automaticSellingMonitorSystem/page/system/systemfunction";
	}

	@RequestMapping("/systemuser/queryuserlist")
	public @ResponseBody
	Map<String, Object> queryuserlist(String conditionStr) {
		UserTableCondition condition = ParseJackson.parseStringToObject(
				conditionStr, UserTableCondition.class);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<SystemUser> datas = systemUserService
					.getUsersByCondition(condition);
			PageInfo pageInfo = new PageInfo();
			int size = systemUserService.getUsersSizeByCondition(condition);
			pageInfo.setCountTotal(size);
			map.put("pageInfo", pageInfo);
			if (datas.size() > 0) {
				map.put("flag", "empty");
			} else {
				map.put("flag", "data");
			}
			map.put("datas", datas);
			map.put("status", "succeed");
		} catch (Exception e) {
			map.put("status", "failure");
			logger.warn(e.getMessage(), e);
		}
		return map;
	}

	@RequestMapping("/systemuser/adduser")
	public @ResponseBody
	Map<String, Object> addUser(SystemUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = true;
		try {
			UserTableCondition condition = new UserTableCondition();
			condition.setUserName(user.getUserName());
			List<SystemUser> users = systemUserService
					.getUsersByCondition(condition);
			if (users.size() > 0) {
				map.put("status", "fail");
				map.put("alerts", "用户名已存在");
				return map;
			}
			user.setDateTime(new Date());
			user.setUpdateTime(new Date());
			flag = systemUserService.saveSystemUser(user);
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
		if (flag) {
			map.put("status", "succeed");
		} else {
			map.put("status", "fail");
		}
		return map;
	}

	@RequestMapping("/systemuser/viewuser")
	public String alerts(ModelMap model, String userId) {
		SystemUser sysuser = systemUserService.getUserById(userId);
		model.put("sysuser", sysuser);
		return "/automaticSellingMonitorSystem/page/system/systemuser_view";
	}

	@RequestMapping("/systemuser/toupdateuser")
	public String toUpdate(ModelMap model, String userId) {
		SystemUser sysuser = systemUserService.getUserById(userId);
		model.put("sysuser", sysuser);
		return "/automaticSellingMonitorSystem/page/system/systemuser_update";
	}

	@RequestMapping("/systemuser/updateuser")
	public @ResponseBody
	Map<String, Object> updateUser(HttpSession session, SystemUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = true;
		try {
			user.setUpdateTime(new Date());
			SystemUser userDB = systemUserService.updateSystemUser(user);
			SystemUser userSession = (SystemUser) session
					.getAttribute(GlobalConstant.SYS_USER);
			if (userDB.getUserId() == userSession.getUserId()) {
				updateSession4User(session);
				updateSession4Menu(session);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag) {
			map.put("status", "succeed");
		} else {
			map.put("status", "fail");
		}
		return map;
	}

	@RequestMapping("/systemuser/deluser")
	public @ResponseBody
	Map<String, Object> delUser(HttpSession session, ModelMap model,
			String userid) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = true;
		try {
			SystemUser userSession = (SystemUser) session
					.getAttribute(GlobalConstant.SYS_USER);
			if (Integer.parseInt(userid) == userSession.getUserId()) {
				map.put("status", "fail");
				map.put("alerts", "不能删除当前登录的用户");
				return map;
			}
			flag = systemUserService.deleteUserById(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag) {
			map.put("status", "succeed");
		} else {
			map.put("status", "fail");
		}
		return map;
	}

	@RequestMapping("/systemuser/queryrolelist")
	public @ResponseBody
	Map<String, Object> queryrolelist(String conditionStr) {
		UserTableCondition condition = ParseJackson.parseStringToObject(
				conditionStr, UserTableCondition.class);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<SystemRole> datas = systemUserService
					.getRolesByCondition(condition);
			PageInfo pageInfo = new PageInfo();
			int size = systemUserService.getRolesSizeByCondition(condition);
			pageInfo.setCountTotal(size);
			map.put("pageInfo", pageInfo);
			if (datas.size() > 0) {
				map.put("flag", "empty");
			} else {
				map.put("flag", "data");
			}
			map.put("datas", datas);
			map.put("status", "succeed");
		} catch (Exception e) {
			map.put("status", "failure");
			logger.warn(e.getMessage(), e);
		}
		return map;
	}

	@RequestMapping("/systemuser/addrole")
	public @ResponseBody
	Map<String, Object> addrole(HttpSession session, SystemRole systemRole) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = true;
		try {
			SystemUser systemUser = (SystemUser) session
					.getAttribute(GlobalConstant.SYS_USER);
			systemRole.setUpdateUser(systemUser.getUserName());
			systemRole.setCreateUser(systemUser.getUserName());
			systemRole.setCreateTime(new Date());
			systemRole.setUpdateTime(new Date());
			flag = systemUserService.saveRole(systemRole);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag) {
			map.put("status", "succeed");
		} else {
			map.put("status", "fail");
		}

		return map;
	}

	@RequestMapping("/systemuser/viewrole")
	public String viewrole(ModelMap model, String roleId) {
		SystemRole systemRole = systemUserService.getRoleById(roleId);
		model.put("sysrole", systemRole);
		return "/automaticSellingMonitorSystem/page/system/systemrole_view";
	}

	@RequestMapping("/systemuser/toupdaterole")
	public String toupdaterole(ModelMap model, String roleId) {
		SystemRole systemRole = systemUserService.getRoleById(roleId);
		model.put("sysrole", systemRole);
		return "/automaticSellingMonitorSystem/page/system/systemrole_update";
	}

	@RequestMapping("/systemuser/updaterole")
	public @ResponseBody
	Map<String, Object> updaterole(HttpSession session, SystemRole systemRole) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (systemRole == null || systemRole.getId() == null) {
			map.put("status", "fail");
			return map;
		}
		boolean flag = true;
		try {
			SystemUser systemUser = (SystemUser) session.getAttribute(GlobalConstant.SYS_USER);
			systemRole.setUpdateUser(systemUser.getUserName());
			systemRole.setUpdateTime(new Date());
			SystemRole roleDB = systemUserService.updateRole(systemRole);
			List<String> userIds = systemUserService.getUsersByRoleId(roleDB.getId() + "");
			SystemUser userSession = (SystemUser) session.getAttribute(GlobalConstant.SYS_USER);
			if (userIds.contains(userSession.getUserId() + "")) {
				updateSession4Menu(session);
			}
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
		if (flag) {
			map.put("status", "succeed");
		} else {
			map.put("status", "fail");
		}
		return map;
	}

	@RequestMapping("/systemuser/deleterole")
	public @ResponseBody
	Map<String, Object> deleterole(HttpSession session, ModelMap model,
			String roleId) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = true;
		try {
			List<String> userIds = systemUserService.getUsersByRoleId(roleId);
			SystemUser userSession = (SystemUser) session
					.getAttribute(GlobalConstant.SYS_USER);
			if (userIds.contains(userSession.getUserId() + "")) {
				map.put("status", "fail");
				map.put("alerts", "删除角色与当前登录用户有关联关系,不能删除");
				return map;
			}
			flag = systemUserService.deleteRoleById(roleId);
		} catch (Exception e) {
			map.put("status", "fail");
			logger.warn(e.getMessage(), e);
		}
		if (flag) {
			map.put("status", "succeed");
		} else {
			map.put("status", "fail");
		}
		return map;
	}

	@RequestMapping("/systemuser/queryfunctionlist")
	public @ResponseBody
	Map<String, Object> queryfunctionlist(String conditionStr) {
		UserTableCondition condition = ParseJackson.parseStringToObject(
				conditionStr, UserTableCondition.class);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<SystemFunction> datas = systemUserService
					.getFunctionsByCondition(condition);
			PageInfo pageInfo = new PageInfo();
			int size = systemUserService.getFunctionsSizeByCondition(condition);
			pageInfo.setCountTotal(size);
			map.put("pageInfo", pageInfo);
			if (datas.size() > 0) {
				map.put("flag", "empty");
			} else {
				map.put("flag", "data");
			}
			map.put("datas", datas);
			map.put("status", "succeed");
		} catch (Exception e) {
			map.put("status", "failure");
			logger.warn(e.getMessage(), e);
		}
		return map;
	}

	@RequestMapping("/systemuser/addfunction")
	public @ResponseBody
	Map<String, Object> addfunction(HttpSession session,
			SystemFunction systemFunction) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = true;
		try {
			SystemUser systemUser = (SystemUser) session.getAttribute(GlobalConstant.SYS_USER);
			systemFunction.setUpdate_user(systemUser.getUserName());
			systemFunction.setCreate_user(systemUser.getUserName());
			systemFunction.setCreate_time(new Date());
			systemFunction.setUpdate_time(new Date());
			flag = systemUserService.saveFucntion(systemFunction);
			updateSession4Menu(session);
		} catch (Exception e) {
			map.put("status", "fail");
			logger.warn(e.getMessage(), e);
		}
		if (flag) {
			map.put("status", "succeed");
		} else {
			map.put("status", "fail");
		}
		return map;
	}

	@RequestMapping("/systemuser/viewfunction")
	public String viewfunction(ModelMap model, String functionId) {
		SystemFunction systemFunction = systemUserService
				.getFunctionById(functionId);
		model.put("sysfunction", systemFunction);
		return "/automaticSellingMonitorSystem/page/system/systemfunction_view";
	}

	@RequestMapping("/systemuser/toupdatefunction")
	public String toupdatefunction(ModelMap model, String functionId) {
		SystemFunction systemFunction = systemUserService.getFunctionById(functionId);
		model.put("sysfunction", systemFunction);
		return "/automaticSellingMonitorSystem/page/system/systemfunction_update";
	}

	@RequestMapping("/systemuser/updatefucntion")
	public @ResponseBody
	Map<String, Object> updatefucntion(HttpSession session,
			SystemFunction systemFunction) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = true;
		try {
			SystemUser systemUser = (SystemUser) session.getAttribute(GlobalConstant.SYS_USER);
			systemFunction.setUpdate_user(systemUser.getUserName());
			systemFunction.setUpdate_time(new Date());
			flag = systemUserService.updateFunction(systemFunction);
			updateSession4Menu(session);
		} catch (Exception e) {
			map.put("status", "fail");
			logger.warn(e.getMessage(), e);
		}
		if (flag) {
			map.put("status", "succeed");
		} else {
			map.put("status", "fail");
		}
		return map;
	}

	@RequestMapping("/systemuser/deletefucntion")
	public @ResponseBody
	Map<String, Object> deletefunction(HttpSession session, ModelMap model,
			String functionId) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = true;
		try {
			SystemUser userSession = (SystemUser) session.getAttribute(GlobalConstant.SYS_USER);
			List<SystemFunction> functions = systemUserService.getFunctionsByUserId(userSession.getUserId());
			for (SystemFunction function : functions) {
				if (!StringUtils.isEmpty(functionId)
						&& functionId.equals(function.getId() + "")) {
					map.put("status", "fail");
					map.put("alerts", "删除菜单与当前登录用户有关联关系,不能删除");
					return map;
				}
			}
			flag = systemUserService.deleteFunctionById(functionId);
		} catch (Exception e) {
			map.put("status", "fail");
			logger.warn(e.getMessage(), e);
		}
		if (flag) {
			map.put("status", "succeed");
		} else {
			map.put("status", "fail");
		}
		return map;
	}

	@RequestMapping("/systemuser/getFunctions4Role")
	public @ResponseBody
	Map<String, Object> getFunctions4Tree(ModelMap model, String roleId) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = true;
		try {
			List<TreeFunction> treefunctions = systemUserService.getTreeFunctions4Role(roleId);
			map.put("zNodes", treefunctions);
		} catch (Exception e) {
			map.put("status", "fail");
			logger.warn(e.getMessage(), e);
		}
		if (flag) {
			map.put("status", "succeed");
		} else {
			map.put("status", "fail");
		}
		return map;
	}

	@RequestMapping("/systemuser/getFunctions4Menu")
	public @ResponseBody
	Map<String, Object> getFunctions4Menu(ModelMap model, String functionId) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = true;
		try {
			List<TreeFunction> treefunctions = systemUserService.getTreeFunctions4Menu(functionId);
			map.put("zNodes", treefunctions);
		} catch (Exception e) {
			map.put("status", "fail");
			logger.warn(e.getMessage(), e);
		}
		if (flag) {
			map.put("status", "succeed");
		} else {
			map.put("status", "fail");
		}
		return map;
	}

	public void updateSession4Menu(HttpSession session) {
//		session.setAttribute(GlobalConstant.SYS_PERMISSION_MENUS, null);
//		session.setAttribute(GlobalConstant.SYS_FISRT_MENUS, null);
		SystemUser user = (SystemUser) session.getAttribute(GlobalConstant.SYS_USER);
		List<SystemFunction> functions = loginService.getPermissionFunctions(user.getUserId());
		session.setAttribute(GlobalConstant.SYS_PERMISSION_MENUS, functions);
//		String fisrtMenu = setFirstMenu(functions);
//		session.setAttribute(GlobalConstant.SYS_FISRT_MENUS, fisrtMenu);
	}

	public void updateSession4User(HttpSession session) {
		SystemUser user = (SystemUser) session.getAttribute(GlobalConstant.SYS_USER);
		SystemUser newUser = systemUserService.getUserById(user.getUserId()+ "");
		session.setAttribute(GlobalConstant.SYS_USER, newUser);
	}
	
	private String setFirstMenu(List<SystemFunction> permissionFunctions) {
		SystemFunction function = getFunction(permissionFunctions);
		if(function!= null){
			return function.getFunction_url();
		}
		return "";
	}

	private SystemFunction getFunction(List<SystemFunction> permissionFunctions) {
		if(permissionFunctions != null && permissionFunctions.size() > 0){
			if(permissionFunctions.get(1) != null){
				List<SystemFunction> cFunctions = permissionFunctions.get(1).getChildFunctions();
				if(cFunctions.size() > 0){
					if(cFunctions.get(0).getChildFunctions().size() > 0){
						return cFunctions.get(0).getChildFunctions().get(0);
					}else {
						return cFunctions.get(0);
					}
				}else {
					return permissionFunctions.get(1);
				}
			}else {
				return null;
			}
		} else {
			return null;
		}
	}
}
