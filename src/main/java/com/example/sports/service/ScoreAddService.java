package com.example.sports.service;

import com.example.sports.dto.PageRequestBean;
import com.example.sports.dto.request.ScoreAddRequest;
import com.example.sports.dto.response.SchoolScoreRes;
import com.example.sports.model.SysProject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-05-02
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
     * 项目校记录
     *
     * @param
     * @return
     */
    List<SchoolScoreRes> schoolScoreInfo(Integer id);

    /**
     * 项目记录表
     *
     * @param
     * @return
     */
    PageInfo<SchoolScoreRes> sysProjectInfo(Integer matchType, PageRequestBean request);

    /**
     * 成绩录入
     *
     * @param
     * @return
     */
    void addScore(ScoreAddRequest request);
}
