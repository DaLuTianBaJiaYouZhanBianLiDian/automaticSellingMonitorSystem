package com.zyuc.common.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import com.zyuc.common.model.SystemFunction;

/**
 * Copyright @ 2012 sohu.com Inc. All right reserved.
 * <p>
 * 权限控制
 * </p>
 * 
 * @author liuchong
 * @since 2012-8-6
 */
public class AuthHandlerInterceptor implements HandlerInterceptor {
    static Logger logger = LoggerFactory.getLogger(AuthHandlerInterceptor.class);

    private String loginPage = "/login";

    private UrlPathHelper urlPathHelper = new UrlPathHelper();

    private PathMatcher pathMatcher = new AntPathMatcher();

    private String[] whitelists;
    private String[] graylists;
    private String[] blacklists;

    // 安全加固
    private boolean secure = false;

    public boolean isSecure() {
        return secure;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String[] getWhitelists() {
        return whitelists;
    }

    public void setWhitelists(String[] whitelists) {
        this.whitelists = whitelists;
    }

    public String[] getBlacklists() {
        return blacklists;
    }

    public void setBlacklists(String[] blacklists) {
        this.blacklists = blacklists;
    }

    public String[] getGraylists() {
        return graylists;
    }

    public void setGraylists(String[] graylists) {
        this.graylists = graylists;
    }

    public PathMatcher getPathMatcher() {
        return pathMatcher;
    }

    public void setPathMatcher(PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestedPath = urlPathHelper.getLookupPathForRequest(request);
        if (whitelists != null) {
            for (String pattern : whitelists) {
                if (pathMatcher.match(pattern, requestedPath))
                    return true;
            }
        }

        // request.getSession().setAttribute("redirect_url", "");
        if (request.getRequestedSessionId() != null) {
            Object user = request.getSession().getAttribute(GlobalConstant.SYS_USER);
            request.getSession().setAttribute("Referer", null);
            if (user != null) {
                ContextUtils.setCurrentUser(user);
                // 是否被选中
                checkActive(request);
                // 灰名单，当用户验证之后，可以访问
                if (graylists != null) {
                    for (String pattern : graylists) {
                        if (pathMatcher.match(pattern, requestedPath)) {
                            return true;
                        }
                    }
                }
                /*
                 * //黑名单 if (blacklists != null) for (String pattern : blacklists) if (pathMatcher.match(pattern, requestedPath)) throw new
                 * ApplicationException("Permission denied."); // ACL权限检查 if (!visitFunction(user.getFunctions(), requestedPath)) { if (secure) {
                 * logger.error("Permission denied. url=" + requestedPath); throw new ApplicationException("Permission denied."); } }
                 */
                return true;
            }
        }
        // response.setContentType("application/json");
        // response.getWriter().write("{status:failure,responsecode:302}");
        // response.getWriter().flush();
        request.getSession().setAttribute("Referer", request.getPathInfo());
        response.sendRedirect(request.getContextPath() + loginPage);
        // 记住要访问的页面
        request.getSession().setAttribute("redirect_url", getRequestURL(request));
        logger.info("需要登录===" + getRequestURL(request));
        return false;
    }

	private void checkActive(HttpServletRequest request) {
		request.getSession().setAttribute(GlobalConstant.SYS_ACTIVE_MENUS,null);
		request.getSession().setAttribute(GlobalConstant.SYS_ACTIVE_MENUS_IDS,null);
		List<SystemFunction> systemFunctions = (List<SystemFunction>) request.getSession().getAttribute(GlobalConstant.SYS_PERMISSION_MENUS);
		List<SystemFunction> terminalFunctions = new ArrayList<SystemFunction>();
		sortFunctionByChild(systemFunctions, terminalFunctions);
		String base = request.getContextPath();
		List<SystemFunction> rightFunctions = new ArrayList<SystemFunction>();
		for(SystemFunction function : terminalFunctions){
			String functionUrl = function.getFunction_url();
			if(function.getFunction_url().contains("?")){
				functionUrl = functionUrl.substring(0, functionUrl.indexOf("?"));
			}
			if(request.getRequestURI().equals(base+functionUrl)){
				rightFunctions.add(function);
			}
		}
		SystemFunction rightFunction = null;
		for(SystemFunction function : rightFunctions){
			if(rightFunction!=null){
				if(Integer.parseInt(rightFunction.getFunction_type()) < Integer.parseInt(function.getFunction_type())){
					rightFunction = function;
				}
			}else {
				rightFunction = function;
			}
		}
		if(rightFunction != null){
			setFunctionPath(request, rightFunction);
		}
	}

	// 产品提议不显示  面包屑导航
	private void setFunctionPath(HttpServletRequest request, SystemFunction function) {
//		String base = request.getContextPath();
		List<String> paths = new ArrayList<String>();
		List<String> ids = new ArrayList<String>();
		List<String> names = new ArrayList<String>();
		paths.add(function.getFunction_url());
		ids.add(function.getId()+"");
		names.add(function.getFunction_name());
		setParentFunctions(function, paths, ids, names);
//		String superpath = "";
		String superIds = "";
		for(int i=paths.size()-1; i>=0; i--){
			String path = paths.get(i);
			String id = ids.get(i);
			String name = names.get(i);
//			superpath += "<li><a href=\"" + base + path +"\">"+name+"</a></li>";
			superIds += id+",";
		}
//		request.getSession().setAttribute(GlobalConstant.SYS_ACTIVE_MENUS,superpath);
		request.getSession().setAttribute(GlobalConstant.SYS_ACTIVE_MENUS_IDS,superIds.substring(0, superIds.length()-1));
	}

	private void setParentFunctions(SystemFunction function, List<String> paths, List<String> ids, List<String> names) {
		if(function.getParentFunction() != null){
			SystemFunction pfunction = function.getParentFunction();
			paths.add(pfunction.getFunction_url());
			ids.add(pfunction.getId()+"");
			names.add(pfunction.getFunction_name());
			setParentFunctions(pfunction, paths, ids, names);
		}
	}

	private void sortFunctionByChild(List<SystemFunction> systemFunctions, List<SystemFunction> terminalFunctions) {
		for(SystemFunction function : systemFunctions){
			if(Integer.parseInt(function.getFunction_type()) == 1){
				terminalFunctions.add(function);
			}
			if(function.getChildFunctions().size() > 0){
				for(SystemFunction cfunction : function.getChildFunctions()){
					cfunction.setParentFunction(function);
					if(!terminalFunctions.contains(cfunction)){
						terminalFunctions.add(cfunction);
					}
					if(cfunction.getChildFunctions().size() > 0){
						sortFunctionByChild(function.getChildFunctions(), terminalFunctions);
					}
				}
				
			}
		}
	}

    public String getRequestURL(HttpServletRequest request) {
        Set<String> keset = new HashSet<String>();
        if (request == null) {
            return "";
        }
        String url = "";
        url = request.getRequestURI().replace(request.getContextPath(), "");

        java.util.Enumeration names = request.getParameterNames();
        int i = 0;
        // if (!StringUtils.isBlank(request.getQueryString())) {
        // url = url + "?" + request.getQueryString();
        // }

        if (names != null) {
            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
                if (keset.contains(name)) {
                    continue;
                } else {
                    keset.add(name);
                }
                if (i == 0) {
                    url = url + "?";
                } else {
                    url = url + "&";
                }
                i++;

                String value = request.getParameter(name);
                if (value == null) {
                    value = "";
                }

                url = url + name + "=" + value;
                try {
                    // java.net.URLEncoder.encode(url, "ISO-8859");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            // String enUrl = java.net.URLEncoder.encode(url, "utf-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return url;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
