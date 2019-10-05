package com.example.sports.service;

import com.example.sports.constant.StatusEnum;
import com.example.sports.dto.PageRequestBean;
import com.example.sports.dto.response.CompetitionResultDTO;
import com.example.sports.dto.response.GroupingProjectInfoDTO;
import com.example.sports.manager.ProjectManager;
import com.example.sports.mapper.*;
import com.example.sports.model.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;;


/**
 * @author xingchao.lxc
 */
@Service
public class SearchServiceImpl implements SearchService {

    private static final Logger log = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Autowired
    private SysProjectMapper sysProjectMapper;

    @Autowired
    private SysGroupingModuleMapper sysGroupingModuleMapper;

    @Autowired
    private ProjectManager projectManager;


    @Override
    public CompetitionResultDTO searchCompetitionStatus(String gameName, int status) {
        CompetitionResultDTO data = new CompetitionResultDTO();
        data.setGameName(gameName);
        data.setStatus(status);
        List<GroupingProjectInfoDTO> pageGroupingList = new ArrayList<>();
        try{
            SysProjectExample example = new SysProjectExample();
            SysProjectExample.Criteria criteria = example.createCriteria();
            criteria.andNameEqualTo(gameName);
            List<SysProject> projectList = sysProjectMapper.selectByExample(example);
            if(projectList == null || projectList.size() <= 0){
                return data;
            }
            int competitionId = projectList.get(0).getId().intValue();
            data.setCompetitionId(competitionId);
            Set<String> projectSet = projectManager.getProjects(competitionId);
            if(CollectionUtils.isNotEmpty(projectSet)){
                projectSet.forEach(project -> {
                    boolean flag = judge(competitionId, project, status);
                    if(flag){
                        pageGroupingList.add(new GroupingProjectInfoDTO(project,
                                projectManager.getProjectName(competitionId, project)));
                    }
                });
            }
        }catch (Exception e){
            log.error("[SearchServiceImpl].searchCompetitionStatus failed! gameName={}, status={}", gameName, status);
        }
        data.setGroupingProjectInfoS(pageGroupingList);
        return data;
    }

    @Override
    public boolean judge(int competitionId, String projectId, int status){
        StatusEnum statusEnum = StatusEnum.getByStatus(status);
        if(statusEnum == null){
            return false;
        }
        SysGroupingModuleExample groupingModuleExample = new SysGroupingModuleExample();
        SysGroupingModuleExample.Criteria groupingModuleCriteria = groupingModuleExample.createCriteria();
        groupingModuleCriteria.addCompetitionId(competitionId);
        groupingModuleCriteria.addProjectId(projectId);
        List<SysGroupingModule> sysGroupingModules = sysGroupingModuleMapper.selectByExample(groupingModuleExample);
        if(CollectionUtils.isNotEmpty(sysGroupingModules)){
            switch (statusEnum){
                //待打印
                case PRINTED:
                    for(SysGroupingModule sysGroupingModule : sysGroupingModules){
                        if(!sysGroupingModule.getCompetitors().equals(sysGroupingModule.getRecords())){
                            return false;
                        }
                    }
                    return true;
                //已完成
                case FINISHED:
                    for(SysGroupingModule sysGroupingModule : sysGroupingModules){
                        if(sysGroupingModule.getPrinted() <= 0){
                            return false;
                        }
                    }
                    return true;
                //进行中
                case PROCEEDING:
                    for(SysGroupingModule sysGroupingModule : sysGroupingModules){
                        if(sysGroupingModule.getCompetitors() > (sysGroupingModule.getRecords())){
                            return true;
                        }
                    }
                    return false;
                default:
                    break;
            }
        }
        return true;
    }
}
