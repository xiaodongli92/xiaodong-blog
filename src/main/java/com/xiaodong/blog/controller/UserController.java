package com.xiaodong.blog.controller;

import com.xiaodong.blog.commons.AppConstants;
import com.xiaodong.blog.model.User;
import com.xiaodong.blog.model.UserInfo;
import com.xiaodong.blog.service.inter.PassportService;
import com.xiaodong.blog.utils.CommonsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiaodong on 15/12/6.
 */
@Controller
@RequestMapping("user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PassportService passportService;

    @RequestMapping("userInfo")
    public String userInfo(HttpServletRequest request){
        Long userId = CommonsUtils.getUserIdFromSession(request);
        User user = passportService.getUserById(userId);
        UserInfo userInfo = passportService.getUserInfoByUserId(userId);
        request.setAttribute("user",user);
        request.setAttribute("userInfo",userInfo);
        return "userInfo";
    }

    @RequestMapping("saveUserInfo")
    public String saveUserInfo(HttpServletRequest request,User user,UserInfo userInfo) {
        LOG.info("User:{},\nUserInfo{}",user,userInfo);
        passportService.update(user);
        passportService.updateUserInfo(userInfo);
        request.getSession().setAttribute(AppConstants.SESSION_USER_NAME,user.getName());
        return "redirect:/user/userInfo.do";
    }
}
