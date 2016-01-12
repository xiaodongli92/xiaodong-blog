package com.xiaodong.blog.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xiaodong on 2015/11/30.
 */
public class PassportInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(PassportInterceptor.class);

    private List<String> loginUrl = Arrays.asList(new String[]{"index.do","home.do","userInfo.do"});

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        String uri = requestUri.substring(requestUri.lastIndexOf("/")+1,requestUri.length());
        LOG.info("ip={},requestUri={}",request.getRemoteAddr(),requestUri);
        if (this.loginUrl.contains(uri)){
            String email = (String) request.getSession().getAttribute("email");
            if (StringUtils.isBlank(email)){
                response.sendRedirect(request.getContextPath()+"/goSignIn.do");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}