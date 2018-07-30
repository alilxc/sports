package com.example.sports.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-05-02
 */
public class RoleModiRequest {
    @ApiModelProperty(value = "学号")
    private Integer sId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "权限名称 1 项目管理 2 成绩查询 3 学生报名管理 4 成绩录入 5 权限管理")
    private ArrayList<Integer> roleId;

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getRoleId() {
    return roleId;
}

    public void setRoleId(ArrayList<Integer> roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "RoleModiRequest{" +
                "sId=" + sId +
                ", name='" + name + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
