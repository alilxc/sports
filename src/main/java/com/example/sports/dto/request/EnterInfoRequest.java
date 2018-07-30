package com.example.sports.dto.request;

import com.example.sports.dto.PageRequestBean;
import io.swagger.annotations.ApiModelProperty;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-05-02
 */
public class EnterInfoRequest extends PageRequestBean{

    @ApiModelProperty(value = "学号")
    private String sysUserSid;

    @ApiModelProperty(value = "学院id")
    private Integer collegeId;

    public String getSysUserSid() {
        return sysUserSid;
    }

    public void setSysUserSid(String sysUserSid) {
        this.sysUserSid = sysUserSid;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
