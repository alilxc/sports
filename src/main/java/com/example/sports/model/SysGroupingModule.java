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

    private int id;

    private long createTime;

    /** 更新时间 */
    private long updateTime;

    /**
     * 赛事id
     */
    private int competitionId;

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
    private int competitors;

    /**
     * 成绩记录数
     */
    private int records;

    /**
     * 是否打印完毕, 1：已打印，0：未打印
     */
    private int printed;

    /**
     * 扩展字段
     */
    private String ext;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
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

    public int getCompetitors() {
        return competitors;
    }

    public void setCompetitors(int competitors) {
        this.competitors = competitors;
    }

    public int getRecords() {
        return records;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public int getPrinted() {
        return printed;
    }

    public void setPrinted(int printed) {
        this.printed = printed;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
