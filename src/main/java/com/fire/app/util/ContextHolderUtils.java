package com.fire.app.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fire.app.common.App;
import com.fire.app.domain.User;
/**
* 上下文工具类 
 */
public class ContextHolderUtils {
	/**
	 * 获取HttpServletRequest
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;

	}
	/**
	 * 获取HttpSession
	 * @return HttpSession
	 */
	public static HttpSession getSession() {
		HttpSession session = getRequest().getSession();
		return session;

	}

	

	public static boolean isLogin() {
	    User user = (User) getSession().getAttribute(App.USER_SESSION_KEY);
	     if (user!=null) {
	         return true;
	     }
        return false;
    }
     
}
