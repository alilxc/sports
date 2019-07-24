package com.example.sports.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;


/**
 * @author xingchao.lxc
 */
public class ProjectRequest implements Serializable{

    @ApiModelProperty(value = "比赛名称")
    private String name;

    @ApiModelProperty(value = "组办方")
    private String organization;

    @ApiModelProperty(value = "比赛起始日期")
    private String activeStart;

    @ApiModelProperty(value = "比赛结束日期")
    private String activeFinish;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActiveStart() {
        return activeStart;
    }

    public void setActiveStart(String activeStart) {
        this.activeStart = activeStart;
    }

    public String getActiveFinish() {
        return activeFinish;
    }

    public void setActiveFinish(String activeFinish) {
        this.activeFinish = activeFinish;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
