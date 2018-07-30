package com.example.sports.model;

import java.io.Serializable;
import java.util.Date;


public class SysUserStudent implements Serializable {
    /**  */
    private Long id;

    /** 学号 */
    private String sysUserSid;

    /** 学院id */
    private Integer sysCollege;

    /** 积分 */
    private Integer integral;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSysUserSid() {
        return sysUserSid;
    }

    public void setSysUserSid(String sysUserSid) {
        this.sysUserSid = sysUserSid == null ? null : sysUserSid.trim();
    }

    public Integer getSysCollege() {
        return sysCollege;
    }

    public void setSysCollege(Integer sysCollege) {
        this.sysCollege = sysCollege;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SysUserStudent{" +
                "id=" + id +
                ", sysUserSid='" + sysUserSid + '\'' +
                ", sysCollege=" + sysCollege +
                ", integral=" + integral +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}