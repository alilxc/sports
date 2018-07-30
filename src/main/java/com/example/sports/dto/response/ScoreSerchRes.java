package com.example.sports.dto.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-05-03
 */
public class ScoreSerchRes {

    @ApiModelProperty(value = "学号")
    private String sysUserSid;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "学生总分")
    private Integer integral;

    @ApiModelProperty(value = "学院名称")
    private String collegename;

    @ApiModelProperty(value = "项目名称")
    private String sysProjectName;

    @ApiModelProperty(value = "组别 1、女子组 2、男子组")
    private Integer teamType;

    @ApiModelProperty(value = "项目类别 1、田赛 2、径赛")
    private Integer sportType;

    @ApiModelProperty(value = "个人成绩")
    private Integer achievement;

    @ApiModelProperty(value = "1、小组赛 2、初赛 3、决赛")
    private Integer matchType;

    public String getSysUserSid() {
        return sysUserSid;
    }

    public void setSysUserSid(String sysUserSid) {
        this.sysUserSid = sysUserSid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }


    public String getCollegename() {
        return collegename;
    }

    public void setCollegename(String collegename) {
        this.collegename = collegename;
    }

    public String getSysProjectName() {
        return sysProjectName;
    }

    public void setSysProjectName(String sysProjectName) {
        this.sysProjectName = sysProjectName;
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

    public Integer getMatchType() {
        return matchType;
    }

    public void setMatchType(Integer matchType) {
        this.matchType = matchType;
    }

    @Override
    public String toString() {
        return "ScoreSerchRes{" +
                "sysUserSid='" + sysUserSid + '\'' +
                ", name='" + name + '\'' +
                ", integral=" + integral +
                ", collegename='" + collegename + '\'' +
                ", sysProjectName='" + sysProjectName + '\'' +
                ", teamType=" + teamType +
                ", sportType=" + sportType +
                ", achievement=" + achievement +
                ", matchType=" + matchType +
                '}';
    }
}
