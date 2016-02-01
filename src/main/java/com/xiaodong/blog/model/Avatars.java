package com.xiaodong.blog.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xiaodong on 2016/2/1.
 *
 * 头像信息
 */
@Entity
@Table(name="AVATARS")
public class Avatars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增长
    private long id = 0 ;

    /**
     * 用户ID
     */
    private long userId;

    /**
     * 头像地址
     */
    @Column(length = 100)
    private String avatarsUrl;

    /**
     * 上传时间
     */
    @Column
    private Date uploadDate;

    /**
     * 状态 0关闭 1启用
     */
    @Column(length = 4)
    private int status;

    @Override
    public String toString() {
        return "Avatars{" +
                "id=" + id +
                ", userId=" + userId +
                ", avatarsUrl='" + avatarsUrl + '\'' +
                ", uploadDate=" + uploadDate +
                ", status=" + status +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAvatarsUrl() {
        return avatarsUrl;
    }

    public void setAvatarsUrl(String avatarsUrl) {
        this.avatarsUrl = avatarsUrl;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
