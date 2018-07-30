package com.example.sports.dto.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-05-02
 */
public class EnterInfoRes {

    @ApiModelProperty(value = "学号")
    private String sysUserSid;

    @ApiModelProperty(value = "学院")
    private String collgeName;

    @ApiModelProperty(value = "姓名")
    private String studentName;

    @ApiModelProperty(value = "项目")
    private String sysProject;

    @ApiModelProperty(value = "组别 1、女子组 2、男子组")
    private Integer teamType;

    @ApiModelProperty(value = "项目类别 1、田赛 2、径赛")
    private Integer sportType;

    @ApiModelProperty(value = "运动员编号")
    private String sportId;

    public String getSysUserSid() {
        return sysUserSid;
    }

    public void setSysUserSid(String sysUserSid) {
        this.sysUserSid = sysUserSid;
    }

    public String getCollgeName() {
        return collgeName;
    }

    public void setCollgeName(String collgeName) {
        this.collgeName = collgeName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSysProject() {
        return sysProject;
    }

    public void setSysProject(String sysProject) {
        this.sysProject = sysProject;
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

    public String getSportId() {
        return sportId;
    }

    public void setSportId(String sportId) {
        this.sportId = sportId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
