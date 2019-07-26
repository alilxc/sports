package com.example.sports.service;

import com.example.sports.dto.PageRequestBean;
import com.example.sports.dto.request.ProjectRequest;
import com.example.sports.dto.response.ProjectRes;
import com.example.sports.mapper.SysProjectMapper;
import com.example.sports.model.SysProject;
import com.example.sports.model.SysProjectExample;
import com.example.sports.util.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * @author xingchao.lxc
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    private static final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);

    private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private SysProjectMapper sysProjectMapper;

    @Override
    public PageInfo<ProjectRes> adminRoleList(PageRequestBean requestBean) {

        SysProjectExample example = new SysProjectExample();
        SysProjectExample.Criteria criteria = example.createCriteria();

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Long today=c.getTimeInMillis()/1000;
        criteria.addActiveFinish(today);

        List<ProjectRes> res = new ArrayList<>();
        PageHelper.startPage(requestBean.getPageNum(), requestBean.getPageSize(), true);
        List<SysProject> sysProjects = sysProjectMapper.selectByExample(example);

        if (sysProjects.size() > 0)
        {
            PageInfo<ProjectRes> pageInfo = new PageInfo(sysProjects);
            for (SysProject sysProject : sysProjects) {
                ProjectRes dto = new ProjectRes();
                dto.setName(sysProject.getName());
                dto.setOrganization(sysProject.getOrganization());
                dto.setId(sysProject.getId());
                String activeStart = DateUtil.scheduleTime(new Date(sysProject.getActiveStart()));
                String activeFinish = DateUtil.scheduleTime(new Date(sysProject.getActiveFinish()));
                dto.setDurationTime(activeStart + "#" + activeFinish);
                res.add(dto);
            }
            PageInfo<ProjectRes> resPageInfo = new PageInfo<>(res);
            resPageInfo.setPageNum(pageInfo.getPageNum());
            resPageInfo.setPageSize(pageInfo.getPageSize());
            resPageInfo.setTotal(pageInfo.getTotal());
            resPageInfo.setPages(pageInfo.getPages());
            return resPageInfo;
        }

        return  null;
    }

    @Override
    public void addProject(ProjectRequest request) {
        try{
            Long timestamp = System.currentTimeMillis();
            SysProject sysProject = new SysProject();
            sysProject.setName(request.getName());
            sysProject.setOrganization(request.getOrganization());
            sysProject.setCreateTime(timestamp);
            sysProject.setUpdateTime(timestamp);
            Long activeStart = format.parse(request.getActiveStart()).getTime();
            sysProject.setActiveStart(activeStart);
            Long activeFinish = format.parse(request.getActiveFinish()).getTime();
            sysProject.setActiveFinish(activeFinish);
            sysProjectMapper.insertSelective(sysProject);
        }catch (Exception e){
            //todo catch exception
            log.error("[ProjectService].addProject exception. {}", request, e);
        }
    }

    @Override
    public void deleteProject(Long id) {
        if (id > 0){
            sysProjectMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void modifyProject(ProjectRequest request, Long id) {
        if (StringUtils.isNotBlank(request.getName()) && StringUtils.isNotBlank(request.getOrganization())
                && id > 0) {
            SysProjectExample example = new SysProjectExample();
            SysProjectExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(id);
            SysProject sysProject = new SysProject();
            sysProject.setName(request.getName());
            sysProject.setOrganization(request.getOrganization());
            sysProjectMapper.updateByExampleSelective(sysProject, example);
        }
    }
}
