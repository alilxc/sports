package com.example.sports.dto.response;

/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-08-22 00:58
 **/
public class SysProjectSignDTO {

    /**
     * 本组顺序
     */
    private long id;

    /** 参赛编号 */
    private String sysUserSid;

    /**
     * 姓名
     */
    private String username;

    /** 所属团队 */
    private String groupName;

    /** 项目代号 */
    private String projectId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 组别
     */
    private String teamType;

    /** 个人成绩 */
    private Double  score;

    public String getSysUserSid() {
        return sysUserSid;
    }

    public void setSysUserSid(String sysUserSid) {
        this.sysUserSid = sysUserSid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTeamType() {
        return teamType;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }

    public Double getScore() {
        return score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
