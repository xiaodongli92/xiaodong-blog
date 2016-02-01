package com.xiaodong.blog.utils;

import com.xiaodong.blog.commons.AppConstants;
import com.xiaodong.blog.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by xiaodong on 2016/1/12.
 */
public class CommonsUtils {

    /**
     * session set attribute
     * @param request
     * @param user
     */
    public static void setSession(HttpServletRequest request, User user){
        HttpSession session = request.getSession();
        session.setAttribute(AppConstants.SESSION_EMAIL,user.getEmail());
        session.setAttribute(AppConstants.SESSION_USER_NAME,user.getName());
        session.setAttribute(AppConstants.SESSION_USER_ID,user.getId());
    }

    /**
     * remove all session attribute
     * @param request
     */
    public static void removeAllSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute(AppConstants.SESSION_EMAIL);
        session.removeAttribute(AppConstants.SESSION_USER_NAME);
        session.removeAttribute(AppConstants.SESSION_USER_ID);
    }

    public static long getUserIdFromSession(HttpServletRequest request) {
        return (Long) request.getSession().getAttribute(AppConstants.SESSION_USER_ID);
    }
}
