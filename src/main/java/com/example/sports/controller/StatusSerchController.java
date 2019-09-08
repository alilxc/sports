package com.example.sports.controller;

import com.example.sports.dto.PageRequestBean;
import com.example.sports.dto.request.EnterInfoRequest;
import com.example.sports.dto.response.*;
import com.example.sports.service.ScoreAddService;
import com.example.sports.service.SearchService;
import com.example.sports.util.ResponseObject;
import com.example.sports.util.ResponseObjectUtil;
import com.example.sports.util.StringUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author xingchao.lxc
 */
@RequestMapping("/statusSerch")
@RestController
@Api(description = "比赛进度查询")
public class StatusSerchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private ScoreAddService scoreAddService;

    @GetMapping("/competition")
    @ApiOperation(value = "比赛进度查询")
    public ResponseObject<CompetitionResultDTO> comptetitionStatus(String gameName, String status,
                                                                   Integer pageNum, Integer pageSize) {
        if(StringUtils.isEmpty(gameName) || StringUtils.isEmpty(status)){
            return ResponseObjectUtil.fail("param is null");
        }
        PageRequestBean requestBean = new PageRequestBean();
        if(pageNum != null){
            requestBean.setPageNum(pageNum);
        }
        if(pageSize != null){
            requestBean.setPageSize(pageSize);
        }
        CompetitionResultDTO data = searchService.searchCompetitionStatus(requestBean, gameName, Integer.valueOf(status));
        return ResponseObjectUtil.success(data);
    }


    @GetMapping("/rank")
    @ApiOperation(value = "查询分组名单信息")
    public ResponseObject<List<CompetitionRankDTO>> queryGroupMember(String competitionId, String projectId){
        if(competitionId == null || StringUtil.isEmpty(projectId)){
            return ResponseObjectUtil.fail("param is null");
        }
        List<CompetitionRankDTO> data = scoreAddService.rank(Integer.valueOf(competitionId), projectId);
        return ResponseObjectUtil.success(data);
    }
}
