package com.example.sports.controller;

import com.example.sports.annotation.Open;
import com.example.sports.dto.request.EnterInfoRequest;
import com.example.sports.dto.request.EnterRequest;
import com.example.sports.dto.request.LoginRequest;
import com.example.sports.dto.request.RegistRequest;
import com.example.sports.dto.response.EnterInfoRes;
import com.example.sports.dto.response.LoginRes;
import com.example.sports.model.SysCollege;
import com.example.sports.service.EnterService;
import com.example.sports.service.EnterServiceImpl;
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
import java.util.List;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-05-02
 */
@RequestMapping("/enterController")
@RestController
@Api(description = "学生报名管理")
public class EnterController {

    @Autowired
    private EnterService enterService;

    @PostMapping("/enter")
    @ApiOperation(value = "报名")
    public ResponseObject<Void> enter(EnterRequest request) {
        enterService.enter(request);
        return ResponseObjectUtil.success();
    }

    @PostMapping("/enterInfo")
    @ApiOperation(value = "报名信息查询")
    public ResponseObject<PageInfo<EnterInfoRes>> enterInfo(EnterInfoRequest request) {
        PageInfo<EnterInfoRes> enterInfo = enterService.enterInfo(request);
        return ResponseObjectUtil.success(enterInfo);
    }

    @PostMapping("/sysCollegeInfo")
    @ApiOperation(value = "学院信息")
    public ResponseObject<List<SysCollege>> sysCollegeInfo() {
        List<SysCollege> sysColleges = enterService.sysCollegeInfo();
        return ResponseObjectUtil.success(sysColleges);
    }

}
