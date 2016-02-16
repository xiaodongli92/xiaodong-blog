package com.xiaodong.blog.model;

import javax.persistence.*;

/**
 * Created by xiaodong on 2016/2/3.
 */
@Entity
@Table(name="CODE_SET")
public class CodeSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增长
    private long id = 0 ;

    /**
     * 代码集名称
     */
    @Column(length = 200)
    private String codeSetName;

    /**
     * 代码集值
     */
    @Column(length = 30)
    private String codeSetValue;

    /**
     * 说明
     */
    @Column(length = 2000)
    private String remark;

    /**
     * 顺序号
     */
    @Column(length = 11,columnDefinition = "default 1")
    private Integer seq;

    /**
     * 状态
     */
    @Column(length = 4,columnDefinition = "default 1")
    private Integer status;

    @Override
    public String toString() {
        return "CodeSet{" +
                "id=" + id +
                ", codeSetName='" + codeSetName + '\'' +
                ", codeSetValue='" + codeSetValue + '\'' +
                ", remark='" + remark + '\'' +
                ", seq=" + seq +
                ", status=" + status +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodeSetName() {
        return codeSetName;
    }

    public void setCodeSetName(String codeSetName) {
        this.codeSetName = codeSetName;
    }

    public String getCodeSetValue() {
        return codeSetValue;
    }

    public void setCodeSetValue(String codeSetValue) {
        this.codeSetValue = codeSetValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
