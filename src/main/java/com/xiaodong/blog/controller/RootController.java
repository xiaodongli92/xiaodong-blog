package com.xiaodong.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xiaodong on 2015/11/13.
 */
@Controller
@RequestMapping("")
public class RootController {

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("about")
    public String about(){
        return "about";
    }

    @RequestMapping("contact")
    public String contact(){
        return "contact";
    }

    @RequestMapping("home")
    public String home(){
        return "home";
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
