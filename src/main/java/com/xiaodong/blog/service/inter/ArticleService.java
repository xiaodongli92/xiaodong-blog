package com.xiaodong.blog.service.inter;

import com.xiaodong.blog.model.Article;

import java.util.List;

/**
 * Created by xiaodong on 2016/2/29.
 */
public interface ArticleService {

    void save(Article article);

    List<Article> pageList(Long authorId);

    Article get(Long id);
}
