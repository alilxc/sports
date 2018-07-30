package com.example.sports.controller;

import com.example.sports.dto.PageRequestBean;
import com.example.sports.dto.request.EnterInfoRequest;
import com.example.sports.dto.response.EnterInfoRes;
import com.example.sports.dto.response.ScoreSerchRes;
import com.example.sports.service.ScoreSerchService;
import com.example.sports.util.ResponseObject;
import com.example.sports.util.ResponseObjectUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-05-02
 */
@RequestMapping("/scoreSerchController")
@RestController
@Api(description = "成绩查询")
public class ScoreSerchController {

    @Autowired
    private ScoreSerchService scoreSerchService;

    @PostMapping("/studentInfo")
    @ApiOperation(value = "个人成绩查询", notes = "sid 为学生学号")
    public ResponseObject<PageInfo<ScoreSerchRes>> studentInfo(PageRequestBean requestBean, Long sid) {
        PageInfo<ScoreSerchRes> scoreSerchResPageInfo = scoreSerchService.studentInfo(requestBean, sid);
        return ResponseObjectUtil.success(scoreSerchResPageInfo);
    }

    @PostMapping("/collegeInfo")
    @ApiOperation(value = "院系成绩查询", notes = "collegeId 为学院id")
    public ResponseObject<PageInfo<ScoreSerchRes>> collegeInfo(PageRequestBean requestBean, Long collegeId) {
        PageInfo<ScoreSerchRes> scoreSerchResPageInfo = scoreSerchService.collegeInfo(requestBean, collegeId);
        return ResponseObjectUtil.success(scoreSerchResPageInfo);
    }
}
