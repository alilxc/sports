package com.example.sports.service;

import com.example.sports.dto.PageRequestBean;
import com.example.sports.dto.response.RoleRes;
import com.example.sports.dto.response.ScoreSerchRes;
import com.github.pagehelper.PageInfo;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-05-02
 */
public interface ScoreSerchService {

    /**
     * 个人成绩查询
     *
     * @param requestBean
     * @return
     */
    PageInfo<ScoreSerchRes> studentInfo(PageRequestBean requestBean, Long sid);

    /**
     * 院系成绩查询
     *
     * @param requestBean
     * @return
     */
    PageInfo<ScoreSerchRes> collegeInfo(PageRequestBean requestBean, Long collegeId);
}
