package com.xiaodong.blog.dao;

import com.xiaodong.blog.model.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by xiaodong on 2016/2/29.
 */
public interface ArticleDAO extends CrudRepository<Article,Long> {

    @Query(value = "SELECT * FROM article ORDER BY create_time desc",nativeQuery = true)
    List<Article> allArticle();

    @Query(value = "SELECT * FROM article WHERE author_id=? ORDER BY create_time desc",nativeQuery = true)
    List<Article> myArticle(Long authorId);
}
