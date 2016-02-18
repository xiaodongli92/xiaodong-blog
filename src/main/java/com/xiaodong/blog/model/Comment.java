package com.xiaodong.blog.model;

import javax.persistence.*;

/**
 * Created by xiaodong on 2016/2/18.
 */
@Entity
@Table(name="COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增长
    private long id = 0 ;

    /**
     * 文章ID
     */
    @Column
    private long articleId;

    /**
     * 评论用户ID
     */
    @Column
    private long commentUserId;

    /**
     * 评论
     */
    @Lob
    private String comment;

    /**
     * 状态
     */
    @Column
    private int status;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", commentUserId=" + commentUserId +
                ", comment='" + comment + '\'' +
                ", status=" + status +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public long getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(long commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
