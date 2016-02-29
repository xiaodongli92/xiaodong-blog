package com.xiaodong.blog.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xiaodong on 2016/2/18.
 */
@Entity
@Table(name="ARTICLE")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增长
    private long id = 0 ;

    /**
     * 标题
     */
    @Column(length = 200)
    private String title;

    /**
     * 类别编码
     */
    @Column(length = 6)
    private String typeCode;

    /**
     * 作者
     */
    @Column
    private long authorId;

    /**
     * 内容
     */
    @Lob
    private String content;

    /**
     * 状态 0 隐私 1 公开
     */
    @Column(columnDefinition = "default 1")
    private int status;

    /**
     * 创建时间
     */
    @Column
    private Date createTime;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", typeCode='" + typeCode + '\'' +
                ", authorId=" + authorId +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
