package com.zyuc.system.dao;

import java.util.List;
import java.util.Map;

import com.zyuc.common.model.SystemFunction;
import com.zyuc.system.model.SystemUser;

public interface ILoginMapper {

	public SystemUser checkUser(Map<String, Object> params);

	public List<SystemFunction> getFunctions(Integer userId);

}