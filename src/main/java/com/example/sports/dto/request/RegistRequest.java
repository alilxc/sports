package com.example.sports.dto.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-04-24
 */
public class RegistRequest {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "学院id")
    private int collegeId;

    @ApiModelProperty(value = "学号/教师编号")
    private String sid;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "账号类型1、各院运动会负责人 2、体育学院教师 3、普通学生和教师 ")
    private Short type;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "确认密码")
    private String rePassword;

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
