package com.xiaodong.blog.controller;

import com.xiaodong.blog.commons.AppConstants;
import com.xiaodong.blog.model.User;
import com.xiaodong.blog.service.inter.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String userInfo(HttpServletRequest request,Model model){
        String email = (String)request.getSession().getAttribute(AppConstants.SESSION_EMAIL);
        User user = passportService.getUserByEmail(email);
        model.addAttribute("user",user);
        return "personalInfo";
    }
}
