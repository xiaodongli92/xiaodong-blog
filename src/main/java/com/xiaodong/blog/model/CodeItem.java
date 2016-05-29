package com.xiaodong.blog.model;

import javax.persistence.*;

/**
 * Created by xiaodong on 2016/2/3.
 */
@Entity
@Table(name="CODE_ITEM")
public class CodeItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增长
    private long id = 0 ;

    /**
     * 所属代码集code
     */
    @Column(length = 30)
    private String codeSet;

    /**
     * 上级代码code
     */
    @Column(length = 30)
    private String parentCode;

    /**
     * 代码名称
     */
    @Column(length = 200)
    private String codeName;

    /**
     * 代码名称2
     */
    @Column(length = 200)
    private String codeName2;

    /**
     * 代码值
     */
    @Column(length = 30)
    private String codeValue;

    /**
     * 顺序号
     */
    @Column(length = 11,columnDefinition = "default 1")
    private Integer seq;

    /**
     * 备注
     */
    @Column(length = 2000)
    private String remark;

    /**
     * 状态 1 启用 0 停用
     */
    @Column(length = 4,columnDefinition = "default 1")
    private Integer status;

    @Override
    public String toString() {
        return "CodeItem{" +
                "id=" + id +
                ", codeSet='" + codeSet + '\'' +
                ", parentCode='" + parentCode + '\'' +
                ", codeName='" + codeName + '\'' +
                ", codeName2='" + codeName2 + '\'' +
                ", codeValue='" + codeValue + '\'' +
                ", seq=" + seq +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodeSet() {
        return codeSet;
    }

    public void setCodeSet(String codeSet) {
        this.codeSet = codeSet;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getCodeName2() {
        return codeName2;
    }

    public void setCodeName2(String codeName2) {
        this.codeName2 = codeName2;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
