package com.example.sports.service;

import com.example.sports.constant.ErrorContants;
import com.example.sports.constant.Errors;
import com.example.sports.dto.request.EnterInfoRequest;
import com.example.sports.dto.request.EnterRequest;
import com.example.sports.dto.response.EnterInfoRes;
import com.example.sports.exception.BusinessException;
import com.example.sports.mapper.*;
import com.example.sports.model.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author xingchao.lxc
 */
@Service
public class EnterServiceImpl implements EnterService {

    @Autowired
    private SysProjectSignMapper sysProjectSignMapper;

    @Autowired
    private SysCollegeMapper sysCollegeMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysProjectMapper sysProjectMapper;

    @Autowired
    private SysUserStudentMapper sysUserStudentMapper;

    @Override
    public void enter(EnterRequest request) {
        SysUserStudentExample example = new SysUserStudentExample();
        SysUserStudentExample.Criteria criteria = example.createCriteria();
        criteria.andSysUserSidEqualTo(request.getSysUserSid());
        List<SysUserStudent> sysUsers = sysUserStudentMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(sysUsers)) {
            throw new BusinessException(Errors.SYSTEM_DATA_NOT_FOUND);
        }
        Integer sysCollege = sysUsers.get(0).getSysCollege();

        SysProjectSign sysProjectSign = new SysProjectSign();
        sysProjectSign.setSysCollegeId(sysCollege);
        sysProjectSign.setSysProjectId(request.getSysProjectId());
        sysProjectSign.setSportType(request.getSportType());
        sysProjectSign.setSysUserSid(request.getSysUserSid());
        sysProjectSign.setSportId(new Date().toString().substring(5, 10));
        sysProjectSign.setTeamType(request.getTeamType());
//        sysProjectSign.setSysCollegeId(request.getCollegeId());
        sysProjectSignMapper.insertSelective(sysProjectSign);
    }

    @Override
    public PageInfo<EnterInfoRes> enterInfo(EnterInfoRequest request) {

        //查询projectsign表
        SysProjectSignExample example = new SysProjectSignExample();
        SysProjectSignExample.Criteria criteria = example.createCriteria();

        //查询user表 取出name字段
        SysUserExample example1 = new SysUserExample();
        SysUserExample.Criteria criteria1 = example1.createCriteria();

        if (StringUtils.isNotBlank(request.getSysUserSid()) && request.getCollegeId() != null && request.getCollegeId() > 0)
        {
            criteria.andSysUserSidEqualTo(request.getSysUserSid());
            criteria.andSysCollegeIdEqualTo(request.getCollegeId());

            List<EnterInfoRes> res = new ArrayList<>();//新建实体类List
            PageHelper.startPage(request.getPageNum(), request.getPageSize(), true);//启动分页，接下来第一条为分页对象
            List<SysProjectSign> sysProjectSignList = sysProjectSignMapper.selectByExample(example);

            PageInfo<EnterInfoRes> resPage = new PageInfo(sysProjectSignList);//放入pageInfo工具类中

            if (sysProjectSignList.size() > 0) {

                for (SysProjectSign sysProjectSign : sysProjectSignList) {
                    EnterInfoRes dto = new EnterInfoRes();
                    SysCollege sysCollege = sysCollegeMapper.selectByPrimaryKey(Long.valueOf(request.getCollegeId()));

                    dto.setCollgeName(sysCollege.getName());//院系
                    dto.setSysUserSid(sysProjectSign.getSysUserSid());//学号

                    //查user表取name
                    criteria1.andSidEqualTo(request.getSysUserSid());

                    List<SysUser> sysUsers = sysUserMapper.selectByExample(example1);
                    if (sysUsers.size() > 0 && sysProjectMapper.selectByPrimaryKey(sysProjectSign.getSysProjectId().longValue())!=null) { //唯一

                        dto.setSysProject(sysProjectMapper.selectByPrimaryKey(sysProjectSign.getSysProjectId().longValue()).getName());//项目名称
                        dto.setSportType(sysProjectSign.getSportType());//项目类型
                        dto.setSportId(sysProjectSign.getSportId());//运动员编号
                        dto.setTeamType(sysProjectSign.getTeamType());
                        res.add(dto);
                        System.out.println(dto.toString());
                    }
                }
                PageInfo<EnterInfoRes> pageInfo = new PageInfo(res);//将循环遍历后的新建List中元素放入返回PageInfo中
                pageInfo.setPages(resPage.getPages());//总页数
                pageInfo.setTotal(resPage.getTotal());//总条数
                pageInfo.setPageSize(resPage.getPageSize());//每页大小
                pageInfo.setPageNum(resPage.getPageNum());//当前页
                return pageInfo;//返回分页完成的数据
            }
            else {
                throw new BusinessException(ErrorContants.NO_PT,"未查询到相关信息");
            }
        }
        return null;
    }
    @Override
    public List<SysCollege> sysCollegeInfo() {
        SysCollegeExample example = new SysCollegeExample();
        SysCollegeExample.Criteria criteria = example.createCriteria();
        List<SysCollege> sysColleges = sysCollegeMapper.selectByExample(example);
        return sysColleges;
    }
}
