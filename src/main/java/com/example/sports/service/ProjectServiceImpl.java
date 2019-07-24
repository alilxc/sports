package com.example.sports.service;

import com.example.sports.dto.PageRequestBean;
import com.example.sports.dto.request.ProjectRequest;
import com.example.sports.dto.response.ProjectRes;
import com.example.sports.mapper.SysProjectMapper;
import com.example.sports.model.SysProject;
import com.example.sports.model.SysProjectExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author xingchao.lxc
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private SysProjectMapper sysProjectMapper;

    @Override
    public PageInfo<ProjectRes> adminRoleList(PageRequestBean requestBean) {

        SysProjectExample example = new SysProjectExample();
        SysProjectExample.Criteria criteria = example.createCriteria();

        List<ProjectRes> res = new ArrayList<>();
        PageHelper.startPage(requestBean.getPageNum(), requestBean.getPageSize(), true);

        List<SysProject> sysProjects = sysProjectMapper.selectByExample(example);


        if (sysProjects.size()>0)
        {
            PageInfo<ProjectRes> pageInfo = new PageInfo(sysProjects);
            for (SysProject sysProject : sysProjects) {
                ProjectRes dto = new ProjectRes();
                dto.setName(sysProject.getName());
                dto.setId(sysProject.getId());
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
        if (StringUtils.isNotBlank(request.getName()) && StringUtils.isNotBlank(request.getOrganization())) {
            SysProject sysProject = new SysProject();
            sysProject.setName(request.getName());
            sysProject.setOrganization(request.getOrganization());
            sysProjectMapper.insertSelective(sysProject);
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
