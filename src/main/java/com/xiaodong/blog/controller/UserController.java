package com.xiaodong.blog.controller;

import com.xiaodong.blog.interceptor.AuthPermission;
import com.xiaodong.blog.model.User;
import com.xiaodong.blog.model.UserInfo;
import com.xiaodong.blog.service.inter.PassportService;
import com.xiaodong.blog.utils.CommonsUtils;
import com.xiaodong.blog.utils.JsonResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @AuthPermission
    @RequestMapping("userInfo")
    public String userInfo(HttpServletRequest request){
        try {
            Long userId = CommonsUtils.getUserIdFromSession(request);
            User user = passportService.getUserById(userId);
            UserInfo userInfo = passportService.getUserInfoByUserId(userId);
            request.setAttribute("user",user);
            request.setAttribute("userInfo",userInfo);
            return "userInfo";
        } catch (Exception e){
            LOG.error("获取用户信息失败",e);
            request.setAttribute("errMsg",e.getMessage());
            return "error";
        }
    }

    @AuthPermission
    @ResponseBody
    @RequestMapping("saveUserInfo")
    public String saveUserInfo(UserInfo userInfo,HttpServletRequest request) {
        try {
            userInfo.setUserId(CommonsUtils.getUserIdFromSession(request));
            String errMsg = passportService.updateUserInfo(userInfo);
            if (errMsg!=null){
                return JsonResponseUtils.badResult(errMsg);
            }
            return JsonResponseUtils.ok();
        } catch (Exception e){
            LOG.error("保存用户个人资料失败",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }

    @AuthPermission
    @ResponseBody
    @RequestMapping("saveUser")
    public String saveUser(User user,HttpServletRequest request){
        try {
            user.setId(CommonsUtils.getUserIdFromSession(request));
            passportService.update(user);
            CommonsUtils.setSession(request,user);
            return JsonResponseUtils.ok();
        } catch (Exception e){
            LOG.error("保存用户基本信息失败",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }

    @AuthPermission
    @RequestMapping("goUpdatePassword")
    public String goUpdatePassword(){
        return "updatePassword";
    }

    @AuthPermission
    @ResponseBody
    @RequestMapping("updatePassword")
    public String updatePassword(HttpServletRequest request,String oldPassword,String newPassword){
        try {
            LOG.info("oldPassword = {},newPassword = {}",oldPassword,newPassword);
            Long userId = CommonsUtils.getUserIdFromSession(request);
            String msg = passportService.updatePassword(oldPassword,newPassword,userId);
            if (msg != null){
                return JsonResponseUtils.badResult(msg);
            }
            return JsonResponseUtils.ok();
        } catch (Exception e){
            LOG.error("修改密码失败,",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }
}
