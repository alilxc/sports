package com.example.sports.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-08-26 23:31
 **/
@ApiModel
public class ScoreAddRequest implements Serializable{

    @ApiModelProperty(value = "成绩列表")
    private List<ScoreAddInfo> scoreAddInfoList;

    @ApiModelProperty(value = "比赛id")
    private long competitionId;

    @ApiModelProperty(value = "比赛场次")
    private String place;

    public List<ScoreAddInfo> getScoreAddInfoList() {
        return scoreAddInfoList;
    }

    public void setScoreAddInfoList(List<ScoreAddInfo> scoreAddInfoList) {
        this.scoreAddInfoList = scoreAddInfoList;
    }

    public long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(long competitionId) {
        this.competitionId = competitionId;
    }

    public String getPlace() {

        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
