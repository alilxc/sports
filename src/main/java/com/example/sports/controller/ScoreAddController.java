package com.example.sports.controller;

import com.example.sports.dto.PageRequestBean;
import com.example.sports.dto.request.EnterInfoRequest;
import com.example.sports.dto.request.EnterRequest;
import com.example.sports.dto.request.ScoreAddRequest;
import com.example.sports.dto.response.EnterInfoRes;
import com.example.sports.dto.response.SchoolScoreRes;
import com.example.sports.dto.response.SysProjectSignDTO;
import com.example.sports.model.SysCollege;
import com.example.sports.model.SysProject;
import com.example.sports.model.SysProjectSign;
import com.example.sports.service.ImportService;
import com.example.sports.service.ScoreAddService;
import com.example.sports.util.ResponseObject;
import com.example.sports.util.ResponseObjectUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author xingchao.lxc
 */
@RequestMapping("/scoreAddController")
@RestController
@Api(description = "成绩录入")
public class ScoreAddController {

    @Autowired
    private ScoreAddService scoreAddService;

    @Autowired
    private ImportService importService;

    @PostMapping("/sysProject")
    @ApiOperation(value = "项目列表")
    public ResponseObject<List<SysProject>> sysProject() {
        List<SysProject> sysProjects = scoreAddService.sysProject();
        return ResponseObjectUtil.success(sysProjects);
    }

    @PostMapping("/schoolScoreInfo")
    @ApiOperation(value = "项目校记录", notes = "id 为项目id")
    public ResponseObject<List<SchoolScoreRes>> schoolScoreInfo(Integer id) {
        List<SchoolScoreRes> schoolScoreResList = scoreAddService.schoolScoreInfo(id);
        return ResponseObjectUtil.success(schoolScoreResList);
    }

    @PostMapping("/sysProjectInfo")
    @ApiOperation(value = "项目记录表", notes = "Integer matchType 1、小组赛 2、初赛 3、决赛")
    public ResponseObject<PageInfo<SchoolScoreRes>> sysProjectInfo(Integer matchType, PageRequestBean request) {
        PageInfo<SchoolScoreRes> schoolScoreResPageInfo = scoreAddService.sysProjectInfo(matchType, request);
        return ResponseObjectUtil.success(schoolScoreResPageInfo);
    }

    @PostMapping("/addScore")
    @ApiOperation(value = "成绩录入")
    public ResponseObject<Void> addScore(ScoreAddRequest request) {
        scoreAddService.addScore(request);
        return ResponseObjectUtil.success();
    }

    @PostMapping("/importMember")
    @ApiOperation(value = "分组名单导入")
    public ResponseObject<Void> importMember(@RequestParam(value = "fileinfo", required = false) MultipartFile file,
                                             @RequestParam(value = "gameType", required = true) String gameName){
        boolean ret = importService.readExcelFile(file, gameName);
        return ret ? ResponseObjectUtil.success() : ResponseObjectUtil.fail("导入名单失败");
    }

    @GetMapping("/queryGroupMember")
    @ApiOperation(value = "查询分组名单信息")
    public ResponseObject<List<SysProjectSignDTO>> queryGroupMember(String gameName, String place){
        List<SysProjectSignDTO> data = scoreAddService.groupSignMemberInfo(gameName, place);
        return ResponseObjectUtil.success(data);
    }
}
