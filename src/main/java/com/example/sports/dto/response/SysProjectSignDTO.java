package com.example.sports.dto.response;

import java.util.List;

/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-08-22 00:58
 **/
public class SysProjectSignDTO {

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

    /**
     * 用户信息
     */
    private List<UserAchievementInfo> achievementInfoList;

    public SysProjectSignDTO(String groupName, String projectId, String projectName, String teamType){
        this.groupName = groupName;
        this.projectId = projectId;
        this.projectName = projectName;
        this.teamType = teamType;
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

    public List<UserAchievementInfo> getAchievementInfoList() {
        return achievementInfoList;
    }

    public void setAchievementInfoList(List<UserAchievementInfo> achievementInfoList) {
        this.achievementInfoList = achievementInfoList;
    }
}
