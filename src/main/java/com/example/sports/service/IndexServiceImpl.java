package com.example.sports.service;

import com.example.sports.dto.response.SchoolScoreRes;
import com.example.sports.mapper.SysGradingModuleMapper;
import com.example.sports.mapper.sys.SysMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author xingchao.lxc
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private SysMapper sysMapper;


    @Override
    public List<SchoolScoreRes> schoolPScoreInfo() {
        //todo 增加记录
        //List<SchoolScoreRes> res = sysMapper.selectByProject();
        List<SchoolScoreRes> res = new ArrayList<>();
        System.out.println(res.toString());
        return res;
    }
}
