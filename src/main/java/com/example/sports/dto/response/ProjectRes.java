package com.example.sports.dto.response;

import io.swagger.annotations.ApiModelProperty;


/**
 * @author xingchao.lxc
 */
public class ProjectRes {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "比赛名称")
    private String name;

    @ApiModelProperty(value = "组办方")
    private String organization;

    @ApiModelProperty(value = "举办时间")
    private String durationTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(String durationTime) {
        this.durationTime = durationTime;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
