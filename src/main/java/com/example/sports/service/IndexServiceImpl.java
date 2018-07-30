package com.example.sports.service;

import com.example.sports.dto.response.SchoolScoreRes;
import com.example.sports.mapper.SysGradingModuleMapper;
import com.example.sports.mapper.sys.SysMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-05-06
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private SysMapper sysMapper;


    @Override
    public List<SchoolScoreRes> schoolPScoreInfo() {
        List<SchoolScoreRes> res = sysMapper.selectByProject();
        System.out.println(res.toString());
        return res;
    }
}
