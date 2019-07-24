package com.example.sports.dto.response;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * @author xingchao.lxc
 */
public class LoginRes {

    @ApiModelProperty(value = "用户Id")
    private Integer id;
    @ApiModelProperty(value = "学号/教师编号")
    private String sid;
    @ApiModelProperty(value = "账号类型1、各院运动会负责人 2、体育学院教师 3、普通学生和教师")
    private Short type;
    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("权限")
    String roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
