package com.example.sports.dto.response;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-08-28 01:42
 **/
public class CompetitionResultDTO implements Serializable{

    /**
     * 比赛id
     */
    private int competitionId;

    /**
     * 比赛名称
     */
    private String gameName;

    /**
     * 状态
     */
    private int status;

    public List<GroupingProjectInfoDTO> getGroupingProjectInfoS() {
        return groupingProjectInfoS;
    }

    public void setGroupingProjectInfoS(List<GroupingProjectInfoDTO> groupingProjectInfoS) {
        this.groupingProjectInfoS = groupingProjectInfoS;
    }

    private List<GroupingProjectInfoDTO> groupingProjectInfoS;

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
