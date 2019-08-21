package com.example.sports.model;

import java.io.Serializable;
import java.util.Date;


/**
 * @author xingchao.lxc
 */
public class SysProjectSign implements Serializable {

    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 创建时间 */
    private long createTime;

    /** 更新时间 */
    private long updateTime;

    /** 参赛编号 */
    private String sysUserSid;

    /**
     * 姓名
     */
    private String username;

    /**
     * 赛事id
     */
    private Long competitionId;

    /** 所属团队 */
    private String groupName;

    /** 项目代号 */
    private String projectId;

    /**
     * 组别
     */
    private String teamType;


    /**
     * 场地
     */
    private String place;

    /** 个人成绩 */
    private Double  score;

    /**
     * 扩展字段信息
     */
    private String ext;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTeamType() {
        return teamType;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}