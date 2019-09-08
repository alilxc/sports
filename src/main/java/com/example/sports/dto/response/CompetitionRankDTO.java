package com.example.sports.dto.response;

import java.io.Serializable;
import java.util.List;

/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-09-02 01:31
 **/
public class CompetitionRankDTO implements Serializable{

    private String teamType;

    private List<UserAchievementDetail> achievementDetailList;

    public String getTeamType() {
        return teamType;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }

    public List<UserAchievementDetail> getAchievementDetailList() {
        return achievementDetailList;
    }

    public void setAchievementDetailList(List<UserAchievementDetail> achievementDetailList) {
        this.achievementDetailList = achievementDetailList;
    }
}
