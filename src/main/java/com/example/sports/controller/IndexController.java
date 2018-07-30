package com.example.sports.controller;

import com.example.sports.dto.response.SchoolScoreRes;
import com.example.sports.service.IndexService;
import com.example.sports.service.ScoreAddService;
import com.example.sports.util.ResponseObject;
import com.example.sports.util.ResponseObjectUtil;
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
@RequestMapping("/indexController")
@RestController
@Api(description = "首页")
public class IndexController {

    @Autowired
    private IndexService indexService;

    @PostMapping("/schoolPScoreInfo")
    @ApiOperation(value = "项目校记录")
    public ResponseObject<List<SchoolScoreRes>> schoolPScoreInfo() {
        List<SchoolScoreRes> schoolScoreResList = indexService.schoolPScoreInfo();
        return ResponseObjectUtil.success(schoolScoreResList);
    }


}
