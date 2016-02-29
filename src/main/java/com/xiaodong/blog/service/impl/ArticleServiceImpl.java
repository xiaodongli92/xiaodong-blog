package com.xiaodong.blog.service.impl;

import com.xiaodong.blog.dao.ArticleDAO;
import com.xiaodong.blog.model.Article;
import com.xiaodong.blog.service.inter.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        article.setCreateTime(new Date());
        LOG.info("参数{}",article);
        articleDAO.save(article);
    }
}
