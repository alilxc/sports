package com.example.sports.dto.response;

/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-09-02 01:26
 **/
public class UserAchievementDetail extends UserAchievementInfo{

    /**
     * 排名
     */
    private int rank;

    /**
     * 所属团队
     */
    private String groupName;

    private String projectId;

    private String projectName;

    public int getRank() {
        return rank;
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

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public UserAchievementDetail(Integer rank, String groupName, String sysUserId, String userName, double score){
        super(sysUserId, userName, score);
        this.rank = rank;
        this.groupName = groupName;
    }
}
