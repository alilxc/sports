package com.example.sports.service;

import com.example.sports.dto.response.SchoolScoreRes;

import java.util.List;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-05-06
 */
public interface IndexService {

    /**
     * 各院校级记录信息
     *
     * @param
     * @return
     */
    List<SchoolScoreRes> schoolPScoreInfo();
}
