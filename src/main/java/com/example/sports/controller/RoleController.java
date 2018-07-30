package com.example.sports.controller;

import com.example.sports.dto.PageRequestBean;
import com.example.sports.dto.request.EnterInfoRequest;
import com.example.sports.dto.request.EnterRequest;
import com.example.sports.dto.request.RoleModiRequest;
import com.example.sports.dto.response.EnterInfoRes;
import com.example.sports.dto.response.RoleRes;
import com.example.sports.service.RoleService;
import com.example.sports.util.ResponseObject;
import com.example.sports.util.ResponseObjectUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-05-02
 */
@RequestMapping("/roleController")
@RestController
@Api(description = "权限管理")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/addAdmin")
    @ApiOperation(value = "添加新的管理员")
    public ResponseObject<Void> addAdmin(RoleModiRequest request) {
        roleService.addAdmin(request);
        return ResponseObjectUtil.success();
    }

    @PostMapping("/modifyAdmin")
    @ApiOperation(value = "修改管理员权限", notes = "id为用户id ， List<Integer> roleId为权限id集合")
    public ResponseObject<Void> modifyAdmin(Long id, String[] roleId, Integer sId) {
        roleService.modifyAdmin(id, roleId,sId);
        return ResponseObjectUtil.success();
    }

    @PostMapping("/delectAdmin")
    @ApiOperation(value = "删除管理员")
    public ResponseObject<Void> delectAdmin(Long id) {
        roleService.delectAdmin(id);
        return ResponseObjectUtil.success();
    }

    @PostMapping("/adminRoleList")
    @ApiOperation(value = "报名信息查询")
    public ResponseObject<PageInfo<RoleRes>> adminRoleList(PageRequestBean requestBean) {
        PageInfo<RoleRes> resPageInfo = roleService.adminRoleList(requestBean);
        return ResponseObjectUtil.success(resPageInfo);
    }
}
