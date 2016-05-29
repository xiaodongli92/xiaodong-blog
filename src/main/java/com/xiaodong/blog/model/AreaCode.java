package com.xiaodong.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by xiaodong on 2016/2/2.
 */
@Entity
@Table(name="AREA_CODE")
public class AreaCode {

    @Id
    @Column(length = 6)
    private String code;

    @Column(length = 30)
    private String name;

    @Column(length = 1)
    private String status;

    @Column(length = 1)
    private String isZxs;

    @Override
    public String toString() {
        return "AreaCode{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", isZxs='" + isZxs + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsZxs() {
        return isZxs;
    }

    public void setIsZxs(String isZxs) {
        this.isZxs = isZxs;
    }
}
