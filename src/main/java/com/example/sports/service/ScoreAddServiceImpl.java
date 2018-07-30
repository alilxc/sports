package com.example.sports.service;

import com.example.sports.dto.PageRequestBean;
import com.example.sports.dto.request.ScoreAddRequest;
import com.example.sports.dto.response.SchoolScoreRes;
import com.example.sports.mapper.*;
import com.example.sports.mapper.sys.SysMapper;
import com.example.sports.model.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-05-02
 */
@Service
public class ScoreAddServiceImpl implements ScoreAddService {

    @Autowired
    private SysGradingModuleMapper sysGradingModuleMapper;

    @Autowired
    private SysProjectMapper sysProjectMapper;

    @Autowired
    private SysUserStudentMapper sysUserStudentMapper;

    @Autowired
    private SysMapper sysMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysCollegeMapper sysCollegeMapper;
    @Override
    public List<SysProject> sysProject() {
        SysProjectExample example = new SysProjectExample();
        SysProjectExample.Criteria criteria = example.createCriteria();
        List<SysProject> sysProjects = sysProjectMapper.selectByExample(example);
        return sysProjects;
    }

    @Override
    public List<SchoolScoreRes> schoolScoreInfo(Integer id) {
        List<SchoolScoreRes> res = sysMapper.selectByProjectId(id);
        return res;
    }

    @Override
    public PageInfo<SchoolScoreRes> sysProjectInfo(Integer matchType, PageRequestBean request) {

        if (matchType != null && matchType > 0) {

            //按比赛类别查成绩录入表
            SysGradingModuleExample example = new SysGradingModuleExample();
            SysGradingModuleExample.Criteria criteria = example.createCriteria();
            criteria.andMatchTypeEqualTo(matchType);
            example.setOrderByClause("achievement desc");

            List<SchoolScoreRes> res = new ArrayList<>();
            PageHelper.startPage(request.getPageNum(), request.getPageSize(), true);

            List<SysGradingModule> sysGradingModules = sysGradingModuleMapper.selectByExample(example);

            if (sysGradingModules.size()>0)
            {
                PageInfo<SchoolScoreRes> resPageInfo = new PageInfo(sysGradingModules);

                for ( SysGradingModule sysGradingModule :sysGradingModules)
                {
                    System.out.println(sysGradingModule.toString());
                    //根据id查詢userstudent表
                    SysUserStudent sysUserStudent = sysUserStudentMapper.selectByPrimaryKey(sysGradingModule.getSysUserStudentId().longValue());

                    System.out.println(sysUserStudent.toString());

                    if (sysUserStudent!=null)
                    {
                        //查询user表获取name
                        SysUserExample userExample = new SysUserExample();
                        SysUserExample.Criteria criteria1 = userExample.createCriteria();
                        //查询user表
                        criteria1.andSidEqualTo(sysUserStudent.getSysUserSid());
                        List<SysUser> sysUsers = sysUserMapper.selectByExample(userExample);

                        System.out.println(sysUsers.toString());
                        if (sysUsers.size()>0)
                        {
                            SchoolScoreRes dto = new SchoolScoreRes();
                            dto.setTeamType(sysGradingModule.getTeamType());
                            dto.setSportType(sysGradingModule.getSportType());
                            dto.setAchievement(sysGradingModule.getAchievement());
                            dto.setSid(sysUserStudent.getSysUserSid());
                            dto.setName(sysUsers.get(0).getName());

                            dto.setSysProjectId(sysGradingModule.getSysProjectId());
                            dto.setSysProjectName(sysProjectMapper.selectByPrimaryKey(sysGradingModule.getSysProjectId().longValue()).getName());
                            dto.setSysCollegeName(sysCollegeMapper.selectByPrimaryKey(sysUserStudent.getSysCollege().longValue()).getName());

                            res.add(dto);
                        }
                    }
                    }
                    PageInfo<SchoolScoreRes> pageInfo = new PageInfo<>(res);
                    pageInfo.setPageNum(resPageInfo.getPageNum());
                    pageInfo.setPageSize(resPageInfo.getPageSize());
                    pageInfo.setTotal(resPageInfo.getTotal());
                    pageInfo.setPages(resPageInfo.getPages());
                    return pageInfo;
            }

        }
        return null;
    }

    @Override
    public void addScore(ScoreAddRequest request) {
        if (StringUtils.isNotBlank(request.getSysUserSid())
                && request.getSysProjectId() != null && request.getSysProjectId() > 0
                && request.getAchievement() != null
                && request.getMatchType() != null && request.getMatchType() > 0
                && request.getSportType() != null && request.getSportType() > 0
                && request.getTeamType() != null && request.getTeamType() > 0) {
            SysUserStudentExample example = new SysUserStudentExample();
            SysUserStudentExample.Criteria criteria = example.createCriteria();
            criteria.andSysUserSidEqualTo(request.getSysUserSid());
            List<SysUserStudent> sysUserStudents = sysUserStudentMapper.selectByExample(example);
            Integer sysUserStudentId = null;
            if (sysUserStudents.size() > 0) {
                sysUserStudentId = sysUserStudents.get(0).getId().intValue();
            }
            SysGradingModule sysGradingModule = new SysGradingModule();
            sysGradingModule.setAchievement(request.getAchievement());
            sysGradingModule.setMatchType(request.getMatchType());
            sysGradingModule.setSysUserStudentId(sysUserStudentId);
            sysGradingModule.setSysProjectId(request.getSysProjectId());
            sysGradingModule.setSportType(request.getSportType());
            sysGradingModule.setTeamType(request.getTeamType());
            sysGradingModuleMapper.insertSelective(sysGradingModule);//插入项目成绩中间表

            SysUserStudentExample example1 = new SysUserStudentExample();
            SysUserStudentExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andSysUserSidEqualTo(request.getSysUserSid());
            SysUserStudent student = sysUserStudentMapper.selectByExample(example1).get(0);
            int score = student.getIntegral();
            SysUserStudent sysUserStudent = new SysUserStudent();
            sysUserStudent.setSysUserSid(request.getSysUserSid());
            sysUserStudent.setIntegral(score + request.getAchievement());
            sysUserStudentMapper.updateByExampleSelective(sysUserStudent, example1);// 更新学生账号积分


        }
    }
}
