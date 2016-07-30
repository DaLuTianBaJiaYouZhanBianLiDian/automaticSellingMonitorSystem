package com.zyuc.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.zyuc.common.utils.ContextUtils;
import com.zyuc.common.utils.GlobalConstant;
import com.zyuc.common.utils.ParseJackson;
import com.zyuc.system.model.SystemUser;
import com.zyuc.system.service.ILoginService;
import com.zyuc.system.service.ISystemUserService;

@Controller
public class LoginAction {

	private static final Logger logger = LoggerFactory.getLogger(LoginAction.class);

	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private ISystemUserService systemUserService;
	
	@RequestMapping("/")
	public String index_(ModelMap model) {
		return "redirect:/distribute/flowdirectionstate";
	}

	@RequestMapping("/login")
	public String login(ModelMap model) {
		model.put(GlobalConstant.SYS_NAME, GlobalConstant.SYS_NAME_VALUE);
		return "/automaticSellingMonitorSystem/page/system/login";
	}

	@RequestMapping("/404")
	public String Page404() {
		return "/automaticSellingMonitorSystem/page/external/404";
	}

	@RequestMapping("/500")
	public String Page500() {
		return "/automaticSellingMonitorSystem/page/external/500";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session, ModelMap model) {
		session.invalidate();
		ContextUtils.clearCurrentUser();
		return "redirect:/login";
	}

	@RequestMapping("/au")
	public @ResponseBody
	Map<String, Object> au(HttpServletRequest request, HttpSession session,
			ModelMap model, String username, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		SystemUser user = null;
		if (!StringUtils.isBlank(username)
				&& !StringUtils.isBlank(password)
				&& ((user = loginService.checkUser(username, password)) != null)) {
			session.setAttribute(GlobalConstant.SYS_USER, user);
			List<SystemFunction> functions = loginService.getPermissionFunctions(user.getUserId());
			session.setAttribute(GlobalConstant.SYS_PERMISSION_MENUS, functions);
//			String fisrtMenu = setFirstMenu(functions);
//			session.setAttribute(GlobalConstant.SYS_FISRT_MENUS, fisrtMenu);
			session.setAttribute(GlobalConstant.SYS_NAME, GlobalConstant.SYS_NAME_VALUE);
			ContextUtils.setCurrentUser(user);
			map.put("username", user.getUserName());
			map.put("status", "succeed");
		} else {
			map.put("username", "");
			map.put("status", "failure");
		}
		return map;
	}
	
	private String setFirstMenu(List<SystemFunction> permissionFunctions) {
		SystemFunction function = getFunction(permissionFunctions);
		if(function!= null){
			return function.getFunction_url();
		}
		return "";
	}

	private SystemFunction getFunction(List<SystemFunction> permissionFunctions) {
		if(permissionFunctions.size()>1 && permissionFunctions.get(1) != null){
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
	}
	
}
