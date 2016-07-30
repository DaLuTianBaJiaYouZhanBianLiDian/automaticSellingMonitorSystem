package com.zyuc.common.utils;


/**
 * Copyright @ 2013 sohu.com Inc.
 * All right reserved.
 * <p>
 * 当前请求用户信息
 * </p>
 * @author liuchong
 * @since 2013-1-11
 */
public class ContextUtils {

	private final static ThreadLocal<Object> currentUser = new ThreadLocal<Object>();
	
	public static Object getCurrentUser() {
		return currentUser.get();
	}
	
	public static void setCurrentUser(Object user) {
		currentUser.set(user);
	}
	
	public static void clearCurrentUser() {
		currentUser.set(null);
	}
}
