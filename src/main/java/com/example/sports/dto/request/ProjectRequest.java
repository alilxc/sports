package com.example.sports.dto.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-05-03
 */
public class ProjectRequest {

    @ApiModelProperty(value = "项目名称")
    private String name;

    @ApiModelProperty(value = "校级记录")
    private String score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
