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
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-05-02
 */

@RequestMapping("/projectController")
@RestController
@Api(description = "赛事管理")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/addProject")
    @ApiOperation(value = "添加新的项目")
    public ResponseObject<Void> addProject(ProjectRequest request) {
        if(StringUtils.isEmpty(request.getActiveStart()) || StringUtils.isEmpty(request.getActiveFinish())){
            return ResponseObjectUtil.fail("请选择比赛举办日期");
        }
        if(StringUtils.isEmpty(request.getName())){
            return ResponseObjectUtil.fail("请输入比赛名称");
        }
        if(StringUtils.isEmpty(request.getOrganization())){
            return ResponseObjectUtil.fail("请输入活动举办方");
        }
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
    @ApiOperation(value = "运动会赛事列表")
    public ResponseObject<PageInfo<ProjectRes>> adminRoleList(PageRequestBean requestBean) {
        PageInfo<ProjectRes> resPageInfo = projectService.adminRoleList(requestBean);
        return ResponseObjectUtil.success(resPageInfo);
    }

    @GetMapping("/queryGameList")
    @ApiModelProperty(value = "运功会赛事")
    public ResponseObject<List<String>> queryProceedGames(){
        List<String> data = projectService.queryProceedGames();
        return ResponseObjectUtil.success(data);
    }
}
