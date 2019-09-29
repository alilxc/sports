package com.example.sports.service;

import com.example.sports.dto.PageRequestBean;
import com.example.sports.dto.request.ScoreAddInfo;
import com.example.sports.dto.request.ScoreAddRequest;
import com.example.sports.dto.response.CompetitionRankDTO;
import com.example.sports.dto.response.SchoolScoreRes;
import com.example.sports.dto.response.SysGroupingDetailDTO;
import com.example.sports.dto.response.SysProjectSignDTO;
import com.example.sports.model.SysProject;
import com.github.pagehelper.PageInfo;

import java.io.File;
import java.util.List;


/**
 * @author xingchao.lxc
 */
public interface ScoreAddService {

    /**
     * 项目列表
     *
     * @param
     * @return
     */
    List<SysProject> sysProject();

    /**
     * 成绩录入
     *
     * @param
     * @return
     */
    void addScore(long competitionId, String place, List<ScoreAddInfo> scoreAddInfos);

    /**
     * 查询分组信息
     */
    SysGroupingDetailDTO groupSignMemberInfo(String gameName, String place);

    /**
     *比赛成绩查询
     */
    List<CompetitionRankDTO> rank(Integer competitionId, String projectId);

    /**
     * 下载获奖名单
     */
    File buildCompetitionCandidate(String gameName, int status);
}
