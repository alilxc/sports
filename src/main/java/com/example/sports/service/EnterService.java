package com.example.sports.service;

import com.example.sports.dto.request.EnterInfoRequest;
import com.example.sports.dto.request.EnterRequest;
import com.example.sports.dto.response.EnterInfoRes;
import com.example.sports.model.SysCollege;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-05-02
 */
public interface EnterService {

    /**
     * 报名
     *
     * @param
     * @return
     */
    void enter(EnterRequest request);

    /**
     * 报名信息查询
     *
     * @param
     * @return
     */
    PageInfo<EnterInfoRes> enterInfo(EnterInfoRequest request);

    /**
     * 学院信息
     *
     * @param
     * @return
     */
    List<SysCollege> sysCollegeInfo();





}
