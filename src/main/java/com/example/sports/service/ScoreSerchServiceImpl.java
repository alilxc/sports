package com.example.sports.service;

import com.example.sports.constant.ErrorContants;
import com.example.sports.dto.PageRequestBean;
import com.example.sports.dto.response.ScoreSerchRes;
import com.example.sports.exception.BusinessException;
import com.example.sports.mapper.*;
import com.example.sports.model.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class ScoreSerchServiceImpl implements ScoreSerchService {

    @Autowired
    private SysUserStudentMapper sysUserStudentMapper;

    @Autowired
    private SysGradingModuleMapper sysGradingModuleMapper;

    @Autowired
    private SysCollegeMapper sysCollegeMapper;

    @Autowired
    private SysProjectMapper sysProjectMapper;



    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public PageInfo<ScoreSerchRes> studentInfo(PageRequestBean requestBean, Long sid) {

        if (sid != null && sid > 0) {
            //查userstudent表取到collegeid和integra
            SysUserStudentExample studentExample = new SysUserStudentExample();
            SysUserStudentExample.Criteria criteriaS = studentExample.createCriteria();
            criteriaS.andSysUserSidEqualTo(String.valueOf(sid));
            List<SysUserStudent> sysUserStudents = sysUserStudentMapper.selectByExample(studentExample);
            SysUserStudent sysUserStudent =null;

            //查sys_user表取name
            SysUserExample userExample = new SysUserExample();
            SysUserExample.Criteria criteria1 = userExample.createCriteria();
            criteria1.andSidEqualTo(String.valueOf(sid));
            List<SysUser> sysUsers = sysUserMapper.selectByExample(userExample);
            SysUser sysUser = null;
            if (sysUsers.size() >0 && sysUserStudents.size() >0)
            {
                sysUser = sysUsers.get(0);
                System.out.println(sysUser.toString());
                sysUserStudent = sysUserStudents.get(0);
                System.out.println(sysUserStudents.toString());

                Long id = sysUserStudent.getId();//学生账号表id
                Long sys_user_id=sysUser.getId();//用户表id


                //查gradle表取projectid和achieve 获取学生成绩集合
                SysGradingModuleExample example = new SysGradingModuleExample();
                SysGradingModuleExample.Criteria criteria = example.createCriteria();
                criteria.andSysUserStudentIdEqualTo((int) id.intValue());
                List<SysGradingModule> sysGradingModules = sysGradingModuleMapper.selectByExample(example);

                List<ScoreSerchRes> res = new ArrayList<>();
                PageHelper.startPage(requestBean.getPageNum(), requestBean.getPageSize(), true);
                PageInfo<ScoreSerchRes> pageInfo = new PageInfo(sysGradingModules);

                if (sysGradingModules.size() > 0)
                {
                    for (SysGradingModule sysGradingModule : sysGradingModules) {


                        if (sysProjectMapper.selectByPrimaryKey(sysGradingModule.getSysProjectId().longValue())!=null)
                        {
                            ScoreSerchRes dto = new ScoreSerchRes();
                            dto.setSportType(sysGradingModule.getSportType());
                            dto.setAchievement(sysGradingModule.getAchievement());
                            dto.setTeamType(sysGradingModule.getTeamType());
                            dto.setMatchType(sysGradingModule.getMatchType());
                            dto.setSysProjectName(sysProjectMapper.selectByPrimaryKey(sysGradingModule.getSysProjectId().longValue()).getName());

                            //学号
                            dto.setSysUserSid(String.valueOf(sid));
                            dto.setIntegral(sysUserStudent.getIntegral());

                            //姓名
                            dto.setName(sysUser.getName());

                            //查询学院名
                            dto.setCollegename(sysCollegeMapper.selectByPrimaryKey(sysUserStudent.getSysCollege().longValue()).getName());

                            res.add(dto);
                            System.out.println(dto.toString());
                        }

                        else {
                            throw new BusinessException(ErrorContants.NO_PT_USER,"未录入成绩");
                        }

                    }
                }
                PageInfo<ScoreSerchRes> resPageInfo = new PageInfo<>(res);
                resPageInfo.setPageNum(pageInfo.getPageNum());
                resPageInfo.setPageSize(pageInfo.getPageSize());
                resPageInfo.setTotal(pageInfo.getTotal());
                resPageInfo.setPages(pageInfo.getPages());
                return resPageInfo;
            }
        }
        return null;
    }

    @Override
    public PageInfo<ScoreSerchRes> collegeInfo(PageRequestBean requestBean, Long collegeId) {
        if (collegeId != null && collegeId > 0) {
            SysUserStudentExample studentExample = new SysUserStudentExample();
            SysUserStudentExample.Criteria criteriaS = studentExample.createCriteria();
            criteriaS.andSysCollegeEqualTo(collegeId.intValue());//学院号
            List<SysUserStudent> sysUserStudents = sysUserStudentMapper.selectByExample(studentExample);

            if (sysUserStudents.size()>0)
            {
                System.out.println(sysUserStudents.toString());

                String sid = null;
                Long suserid = null;
                String collegeName = null;
                Integer integral = null;
                String name=null;
                Long user_id =null;

                //遍历user_student表
                for (SysUserStudent sysUserStudent : sysUserStudents)
                {
                    System.out.println(sysUserStudents.toString());
                    sid = sysUserStudent.getSysUserSid();//学号
                    integral = sysUserStudent.getIntegral();//总分
                    suserid = sysUserStudent.getId();//学生账号表id   不是
                    collegeName = sysCollegeMapper.selectByPrimaryKey(collegeId).getName();//院名

                    //根据学号查询user表，取出name
                    SysUserExample userExample = new SysUserExample();
                    SysUserExample.Criteria criteria1 = userExample.createCriteria();
                    criteria1.andSidEqualTo(String.valueOf(sid));
                    List<SysUser> sysUsers = sysUserMapper.selectByExample(userExample);

                    if (sysUsers.size()>0)
                    {
                      SysUser  sysUser=sysUsers.get(0);
                        name = sysUser.getName(); //姓名
                        user_id = sysUser.getId(); //user表id

                        //sportType teamType matchType achievement projectid 查询学生成绩表
                        SysGradingModuleExample sysGradingModuleExample = new SysGradingModuleExample();
                        SysGradingModuleExample.Criteria criteria = sysGradingModuleExample.createCriteria();
                        criteria.andSysUserStudentIdEqualTo(suserid.intValue());//学生账号表id


                        List<ScoreSerchRes> res = new ArrayList<>();
                        PageHelper.startPage(requestBean.getPageNum(), requestBean.getPageSize(), true);
                        List<SysGradingModule> sysGradingModules = sysGradingModuleMapper.selectByExample(sysGradingModuleExample);
                        PageInfo<ScoreSerchRes> pageInfo = new PageInfo(sysGradingModules);

                        if (sysGradingModules.size()>0)
                        {
                            for (SysGradingModule sysGradingModule : sysGradingModules) {
                                ScoreSerchRes dto = new ScoreSerchRes();
                                dto.setAchievement(sysGradingModule.getAchievement());
                                dto.setSysUserSid(sid);
                                dto.setName(name);
                                dto.setIntegral(integral);
                                dto.setCollegename(collegeName);
                                dto.setMatchType(sysGradingModule.getMatchType());
                                dto.setTeamType(sysGradingModule.getTeamType());
                                dto.setSportType(sysGradingModule.getSportType());
                                dto.setSysProjectName(sysProjectMapper.selectByPrimaryKey(sysGradingModule.getSysProjectId().longValue()).getName());
                                res.add(dto);
                            }
                            PageInfo<ScoreSerchRes> resPageInfo = new PageInfo<>(res);
                            resPageInfo.setPageNum(pageInfo.getPageNum());
                            resPageInfo.setPageSize(pageInfo.getPageSize());
                            resPageInfo.setTotal(pageInfo.getTotal());
                            resPageInfo.setPages(pageInfo.getPages());
                            return resPageInfo;
                        }
                    }
                }
            }
        }
        return null;
    }
}
