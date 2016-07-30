package com.zyuc.common.utils;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

/**
 * Copyright @ 2012 sohu.com Inc.
 * All right reserved.
 * <p>
 * 
 * </p>
 * @author liuchong
 * @since 2012-7-23
 */
public class ExtFreeMarkerView extends FreeMarkerView {
	public static final String CONTEXT_PATH = "base";
	
	@Override
	protected void exposeHelpers(Map<String, Object> model,
			HttpServletRequest request) throws Exception {
		super.exposeHelpers(model, request);
		model.put(CONTEXT_PATH, request.getContextPath());
	}
}
