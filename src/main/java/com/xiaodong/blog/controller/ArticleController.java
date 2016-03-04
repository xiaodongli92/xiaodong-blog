package com.xiaodong.blog.controller;

import com.xiaodong.blog.interceptor.AuthPermission;
import com.xiaodong.blog.model.Article;
import com.xiaodong.blog.service.inter.ArticleService;
import com.xiaodong.blog.utils.CommonsUtils;
import com.xiaodong.blog.utils.JsonResponseUtils;
import com.xiaodong.blog.utils.ValidateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public String toPage(@Param("id") Long id,HttpServletRequest request){
        if (id != null){
            Article article = articleService.get(id);
            request.setAttribute("article",article);
        }
        return "article";
    }

    @AuthPermission
    @ResponseBody
    @RequestMapping("saveArticle")
    public String saveArticle(Article article, HttpServletRequest request){
        try {
            String errMsg = ValidateUtils.articleValidate(article);
            LOG.info("参数{}",article);
            if (errMsg != null){
                return JsonResponseUtils.badResult(errMsg);
            }
            article.setAuthorId(CommonsUtils.getUserIdFromSession(request));
            article.setAuthorName(CommonsUtils.getUserNameFromSession(request));
            articleService.save(article);
            return JsonResponseUtils.ok();
        } catch (Exception e) {
            LOG.error("保存文章失败，",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }

    @AuthPermission
    @RequestMapping("list")
    public String list(HttpServletRequest request){
        try {
            List<Article> list = articleService.pageList();
            request.setAttribute("list",list);
            return "articleList";
        } catch (Exception e){
            LOG.error("获取文章列表失败，",e);
            return JsonResponseUtils.badResult(e.getMessage());
        }
    }
}
