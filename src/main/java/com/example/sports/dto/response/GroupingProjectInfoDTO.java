package com.example.sports.dto.response;

import java.io.Serializable;

/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-08-28 01:59
 **/
public class GroupingProjectInfoDTO implements Serializable{

    /**
     * 项目id
     */
    private String projectId;

    /**
     * 项目名
     */
    private String projectName;

    public GroupingProjectInfoDTO(String projectId, String projectName){
        this.projectId = projectId;
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
