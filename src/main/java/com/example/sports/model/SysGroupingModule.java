package com.example.sports.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: sports
 * @description: 项目分组比赛
 * @author: xingchao.lxc
 * @create: 2019-08-08 00:01
 **/
public class SysGroupingModule implements Serializable{

    private static final long serialVersionUID = -1;

    private Long id;

    private Long createTime;

    /** 更新时间 */
    private Long updateTime;

    /**
     * 赛事id
     */
    private Integer competitionId;

    /**
     * 项目代码
     */
    private String projectId;

    /**
     * 组别
     */
    private String teamType;

    /**
     * 参赛人数
     */
    private Integer competitors;

    /**
     * 成绩记录数
     */
    private Integer records;

    /**
     * 是否打印完毕, 1：已打印，0：未打印
     */
    private Integer printed;

    /**
     * 扩展字段
     */
    private String ext;

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

    public Integer getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
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

    public Integer getCompetitors() {
        return competitors;
    }

    public void setCompetitors(Integer competitors) {
        this.competitors = competitors;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public Integer getPrinted() {
        return printed;
    }

    public void setPrinted(Integer printed) {
        this.printed = printed;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
