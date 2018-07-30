package com.example.sports.dto.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-05-03
 */
public class SchoolScoreRes {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "学号")
    private String sid;

    @ApiModelProperty(value = "项目id")
    private Integer sysProjectId;

    @ApiModelProperty(value = "项目名称")
    private String sysProjectName;

    @ApiModelProperty(value = "学院名称")
    private String sysCollegeName;

    @ApiModelProperty(value = "组别 1、女子组 2、男子组")
    private Integer teamType;

    @ApiModelProperty(value = "项目类别 1、田赛 2、径赛")
    private Integer sportType;

    @ApiModelProperty(value = "个人成绩")
    private Integer achievement;


    public String getSysProjectName() {
        return sysProjectName;
    }

    public void setSysProjectName(String sysProjectName) {
        this.sysProjectName = sysProjectName;
    }

    public String getSysCollegeName() {
        return sysCollegeName;
    }

    public void setSysCollegeName(String sysCollegeName) {
        this.sysCollegeName = sysCollegeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Integer getSysProjectId() {
        return sysProjectId;
    }

    public void setSysProjectId(Integer sysProjectId) {
        this.sysProjectId = sysProjectId;
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

    @Override
    public String toString() {
        return super.toString();
    }
}
