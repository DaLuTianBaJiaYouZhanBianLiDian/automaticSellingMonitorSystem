package com.zyuc.system.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyuc.common.model.SystemFunction;
import com.zyuc.common.utils.GlobalConstant;
import com.zyuc.common.utils.ParseJackson;
import com.zyuc.system.dao.ILoginMapper;
import com.zyuc.system.model.SystemUser;
import com.zyuc.system.service.ILoginService;
import com.zyuc.system.service.ISystemUserService;

@Service
public class LoginServiceImpl implements ILoginService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	private ILoginMapper loginMapper;

	@Autowired
	private ISystemUserService systemUserService;

	@Override
	public List<SystemFunction> getPermissionFunctions(Integer userId) {
		List<SystemFunction> dbFunctions = systemUserService.getFunctionsByUserId(userId);
		System.out.println(ParseJackson.parseObjectToLightString(dbFunctions));
		List<SystemFunction> permissionFunctions = formatePermissionFunctions(dbFunctions);
		if (permissionFunctions == null) {
			return new ArrayList<SystemFunction>();
		}
		showOrderFunctions(permissionFunctions);
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

	private List<SystemFunction> formatePermissionFunctions(List<SystemFunction> dbFunctions) {
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
			List<SystemFunction> permissionFunctions, List<SystemFunction> functions,
			int type) {
		for (SystemFunction function : functions) {
			if (type == Integer.parseInt(function.getFunction_type())) {
				permissionFunctions.add(function);
			}
		}
		return permissionFunctions;
	}

	@Override
	public SystemUser checkUser(String username, String password) {
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("dbsystemname", GlobalConstant.DB_SYSTEM);
		params.put("username", username);
		params.put("password", password);
		SystemUser user = loginMapper.checkUser(params);
		return user;
	}

}
