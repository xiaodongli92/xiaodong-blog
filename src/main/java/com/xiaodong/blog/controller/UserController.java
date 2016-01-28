package com.xiaodong.blog.controller;

import com.xiaodong.blog.commons.AppConstants;
import com.xiaodong.blog.model.User;
import com.xiaodong.blog.service.inter.PassportService;
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

    @Autowired
    private PassportService passportService;

    @RequestMapping("userInfo")
    public String userInfo(){
        return "userInfo";
    }

    @RequestMapping("saveUserInfo")
    public String saveUserInfo(HttpServletRequest request,User user) {
        User newUser = passportService.update(user);
        request.getSession().setAttribute(AppConstants.SESSION_USER,newUser);
        return "redirect:userInfo.do";
    }
}
