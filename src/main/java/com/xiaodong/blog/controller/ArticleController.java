package com.xiaodong.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xiaodong on 2016/2/22.
 */
@Controller
@RequestMapping("article")
public class ArticleController {

    @RequestMapping("toPage")
    public String toPage(){
         return "article";
    }
}
