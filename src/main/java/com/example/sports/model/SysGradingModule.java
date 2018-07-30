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
public class SysGradingModule implements Serializable {
    /**  */
    private Long id;

    /** 学生id */
    private Integer sysUserStudentId;

    /** 项目id */
    private Integer sysProjectId;

    /** 个人成绩 */
    private Integer achievement;

    /** 1、女子组 2、男子组 */
    private Integer teamType;

    /** 1、小组赛 2、初赛 3、决赛 */
    private Integer matchType;

    /** 1、田赛 2、径赛 */
    private Integer sportType;

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

    public Integer getSysUserStudentId() {
        return sysUserStudentId;
    }

    public void setSysUserStudentId(Integer sysUserStudentId) {
        this.sysUserStudentId = sysUserStudentId;
    }

    public Integer getSysProjectId() {
        return sysProjectId;
    }

    public void setSysProjectId(Integer sysProjectId) {
        this.sysProjectId = sysProjectId;
    }

    public Integer getAchievement() {
        return achievement;
    }

    public void setAchievement(Integer achievement) {
        this.achievement = achievement;
    }

    public Integer getTeamType() {
        return teamType;
    }

    public void setTeamType(Integer teamType) {
        this.teamType = teamType;
    }

    public Integer getMatchType() {
        return matchType;
    }

    public void setMatchType(Integer matchType) {
        this.matchType = matchType;
    }

    public Integer getSportType() {
        return sportType;
    }

    public void setSportType(Integer sportType) {
        this.sportType = sportType;
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
        sb.append(", sysUserStudentId=").append(sysUserStudentId);
        sb.append(", sysProjectId=").append(sysProjectId);
        sb.append(", achievement=").append(achievement);
        sb.append(", teamType=").append(teamType);
        sb.append(", matchType=").append(matchType);
        sb.append(", sportType=").append(sportType);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}