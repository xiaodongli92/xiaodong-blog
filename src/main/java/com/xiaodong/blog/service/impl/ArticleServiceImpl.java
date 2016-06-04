package com.xiaodong.blog.service.impl;

import com.xiaodong.blog.dao.ArticleDAO;
import com.xiaodong.blog.model.Article;
import com.xiaodong.blog.service.inter.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by xiaodong on 2016/2/29.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private static final Logger LOG = LoggerFactory.getLogger(ArticleService.class);

    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public void save(Article article) {
        if (article.getId() == 0){
            article.setCreateTime(new Date());
        } else {
            Article oldArticle = articleDAO.findOne(article.getId());
            article.setCreateTime(oldArticle.getCreateTime());
        }
        article.setUpdateTime(new Date());
        articleDAO.save(article);
    }

    @Override
    public List<Article> pageList(Long authorId) {
        return articleDAO.myArticle(authorId);
    }

    @Override
    public Article get(Long id) {
        return articleDAO.findOne(id);
    }
}
