package com.example.sports.service;

import com.example.sports.dto.PageRequestBean;
import com.example.sports.dto.request.ProjectRequest;
import com.example.sports.dto.response.ProjectRes;
import com.github.pagehelper.PageInfo;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-05-02
 */
public interface ProjectService {

    /**
     * 运动会项目列表
     *
     * @param requestBean
     * @return
     */
    PageInfo<ProjectRes> adminRoleList(PageRequestBean requestBean);

    /**
     * 添加新的项目
     *
     * @param
     * @return
     */
    void addProject(ProjectRequest request);

    /**
     * 删除项目
     *
     * @param
     * @return
     */
    void deleteProject(Long id);

    /**
     * 修改项目信息
     *
     * @param
     * @return
     */
    void modifyProject(ProjectRequest request, Long id);

}
