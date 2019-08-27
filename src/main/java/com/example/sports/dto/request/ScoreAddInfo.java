package com.example.sports.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.io.Serializable;


/**
 * @author xingchao.lxc
 */
@ApiModel
public class ScoreAddInfo implements Serializable{

    @ApiModelProperty(value = "团队名称")
    private String groupName;

    @ApiModelProperty(value = "分组中的顺序号,如果是团队则不需要")
    private String id;

    @ApiModelProperty(value = "项目id")
    private String sysProjectId;

    @ApiModelProperty(value = "组别")
    private String teamType;

    @ApiModelProperty(value = "成绩,如果是团体赛的话则代表所有")
    private String score;

    @ApiModelProperty(value = "是否团队分组")
    private boolean team;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSysProjectId() {
        return sysProjectId;
    }

    public void setSysProjectId(String sysProjectId) {
        this.sysProjectId = sysProjectId;
    }

    public String getTeamType() {
        return teamType;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public boolean isTeam() {
        return team;
    }

    public void setTeam(boolean team) {
        this.team = team;
    }

    public String router(){
        return sysProjectId + "#" + teamType;
    }
}
