package com.example.sports.model;

import java.io.Serializable;
import java.util.Date;


/**
 * @author xingchao.lxc
 */
public class SysProject implements Serializable {
    /**  */
    private Long id;

    /** 项目名称 */
    private String name;

    /** 组办方 */
    private String organization;

    /** 创建时间 */
    private Long createTime;

    /** 更新时间 */
    private Long updateTime;

    /**
     *  比赛开始时间
     */
    private Long activeStart;

    /**
     * 比赛结束时间
     */
    private Long activeFinish;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getActiveStart() {
        return activeStart;
    }

    public void setActiveStart(Long activeStart) {
        this.activeStart = activeStart;
    }

    public Long getActiveFinish() {
        return activeFinish;
    }

    public void setActiveFinish(Long activeFinish) {
        this.activeFinish = activeFinish;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", organization=").append(organization);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}