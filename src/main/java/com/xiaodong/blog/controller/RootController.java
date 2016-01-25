package com.xiaodong.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiaodong on 2015/11/13.
 */
@Controller
@RequestMapping("")
public class RootController {

    @RequestMapping("main")
    public String main(){
        return "main";
    }

    @RequestMapping("goSignIn")
    public String goSignIn(){
        return "signIn";
    }

    @RequestMapping("goSignUp")
    public String goSignUp(){
        return "signUp";
    }
}
