package com.example.sports.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @author hjr
 * @date 2018-05-03
 *
 */
public class SysProjectSign implements Serializable {
    /**  */
    private Long id;

    /** 学号 */
    private String sysUserSid;

    /** 学院id */
    private Integer sysCollegeId;

    /** 项目id */
    private Integer sysProjectId;

    /** 运动员号 */
    private String sportId;

    /** 1、女子组 2、男子组 */
    private Integer teamType;

    /** 1、田赛 2、径赛 */
    private Integer sportType;

    /** 个人成绩 */
    private Integer achievement;

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

    public Integer getSysCollegeId() {
        return sysCollegeId;
    }

    public void setSysCollegeId(Integer sysCollegeId) {
        this.sysCollegeId = sysCollegeId;
    }

    public Integer getSysProjectId() {
        return sysProjectId;
    }

    public void setSysProjectId(Integer sysProjectId) {
        this.sysProjectId = sysProjectId;
    }

    public String getSportId() {
        return sportId;
    }

    public void setSportId(String sportId) {
        this.sportId = sportId == null ? null : sportId.trim();
    }

    public Integer getTeamType() {
        return teamType;
    }

    public void setTeamType(Integer teamType) {
        this.teamType = teamType;
    }

    public Integer getSportType() {
        return sportType;
    }

    public void setSportType(Integer sportType) {
        this.sportType = sportType;
    }

    public Integer getAchievement() {
        return achievement;
    }

    public void setAchievement(Integer achievement) {
        this.achievement = achievement;
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
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sysUserSid=").append(sysUserSid);
        sb.append(", sysCollegeId=").append(sysCollegeId);
        sb.append(", sysProjectId=").append(sysProjectId);
        sb.append(", sportId=").append(sportId);
        sb.append(", teamType=").append(teamType);
        sb.append(", sportType=").append(sportType);
        sb.append(", achievement=").append(achievement);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}