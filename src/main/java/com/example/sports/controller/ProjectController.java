package com.example.sports.controller;

import com.example.sports.dto.PageRequestBean;
import com.example.sports.dto.request.EnterInfoRequest;
import com.example.sports.dto.request.ProjectRequest;
import com.example.sports.dto.request.RegistRequest;
import com.example.sports.dto.response.EnterInfoRes;
import com.example.sports.dto.response.ProjectRes;
import com.example.sports.service.ProjectService;
import com.example.sports.util.ResponseObject;
import com.example.sports.util.ResponseObjectUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-05-02
 */

@RequestMapping("/projectController")
@RestController
@Api(description = "项目管理")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/addProject")
    @ApiOperation(value = "添加新的项目")
    public ResponseObject<Void> addProject(ProjectRequest request) {
        projectService.addProject(request);
        return ResponseObjectUtil.success();
    }

    @PostMapping("/deleteProject")
    @ApiOperation(value = "删除项目" , notes = "id为项目id")
    public ResponseObject<Void> deleteProject(Long id) {
        projectService.deleteProject(id);
        return ResponseObjectUtil.success();
    }

    @PostMapping("/modifyProject")
    @ApiOperation(value = "修改项目信息", notes = "id为项目id ")
    public ResponseObject<Void> modifyProject(ProjectRequest request, Long id) {
        projectService.modifyProject(request, id);
        return ResponseObjectUtil.success();
    }

    @PostMapping("/adminRoleList")
    @ApiOperation(value = "运动会项目列表")
    public ResponseObject<PageInfo<ProjectRes>> adminRoleList(PageRequestBean requestBean) {
        PageInfo<ProjectRes> resPageInfo = projectService.adminRoleList(requestBean);
        return ResponseObjectUtil.success(resPageInfo);
    }
}
