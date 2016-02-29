package com.xiaodong.blog.controller;

import com.xiaodong.blog.interceptor.AuthPermission;
import com.xiaodong.blog.model.Article;
import com.xiaodong.blog.service.inter.ArticleService;
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
 * Created by xiaodong on 2016/2/22.
 */
@Controller
@RequestMapping("article")
public class ArticleController {

    private static final Logger LOG = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @AuthPermission
    @RequestMapping("toPage")
    public String toPage(){
         return "article";
    }

    @AuthPermission
    @ResponseBody
    @RequestMapping("saveArticle")
    public String saveArticle(Article article, HttpServletRequest request){
        if (article == null){
            return JsonResponseUtils.badResult("请填写信息");
        }
        try {
            Long id = CommonsUtils.getUserIdFromSession(request);
            article.setAuthorId(id);
            articleService.save(article);
            return JsonResponseUtils.ok();
        } catch (Exception e) {
            LOG.error("保存文章失败，",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }
}
