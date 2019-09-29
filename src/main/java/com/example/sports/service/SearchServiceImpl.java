package com.example.sports.service;

import com.alibaba.fastjson.JSON;
import com.example.sports.constant.StatusEnum;
import com.example.sports.dto.PageRequestBean;
import com.example.sports.dto.response.CompetitionResultDTO;
import com.example.sports.dto.response.GroupingProjectInfoDTO;
import com.example.sports.manager.ProjectManager;
import com.example.sports.mapper.*;
import com.example.sports.model.*;
import com.example.sports.service.excel.ComplexExportExcelManager;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private ComplexExportExcelManager exportExcelManager;

    @Autowired
    private SysProjectSignMapper sysProjectSignMapper;


    @Override
    public CompetitionResultDTO searchCompetitionStatus(PageRequestBean requestBean, String gameName, int status) {
        CompetitionResultDTO data = new CompetitionResultDTO();
        data.setGameName(gameName);
        data.setStatus(status);
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
                    Set<String> teamTypeList = projectManager.getTeamTypeList(competitionId, project);
                    boolean find = true;
                    if(CollectionUtils.isNotEmpty(teamTypeList)){
                        for(String teamType : teamTypeList){
                            find = judge(competitionId, project, teamType, status);
                            if(!find){
                                break;
                            }
                        }
                        if(find){
                            //todo 需要设置projectName
                            data.addGroupingProject(new GroupingProjectInfoDTO(project, "XXXXX"));
                        }
                    }
                });
            }
        }catch (Exception e){
            log.error("[SearchServiceImpl].searchCompetitionStatus failed! gameName={}, status={}", gameName, status);
        }
        return data;
    }

    @Override
    public boolean judge(int competitionId, String projectId, String teamType, int status){
        StatusEnum statusEnum = StatusEnum.getByStatus(status);
        if(statusEnum == null){
            return false;
        }
        boolean result = false;
        SysGroupingModuleExample groupingModuleExample = new SysGroupingModuleExample();
        SysGroupingModuleExample.Criteria groupingModuleCriteria = groupingModuleExample.createCriteria();
        groupingModuleCriteria.addCompetitionId(competitionId);
        groupingModuleCriteria.addProjectId(projectId);
        groupingModuleCriteria.addTeamType(teamType);
        List<SysGroupingModule> sysGroupingModules = sysGroupingModuleMapper.selectByExample(groupingModuleExample);
        if(CollectionUtils.isNotEmpty(sysGroupingModules)){
            SysGroupingModule sysGroupingModule = sysGroupingModules.get(0);
            switch (statusEnum){
                //待打印
                case PRINTED:
                    result = sysGroupingModule.getCompetitors().intValue() == sysGroupingModule.getRecords().intValue();
                    break;
                //已完成
                case FINISHED:
                    result = sysGroupingModule.getPrinted() > 0;
                    break;
                //进行中
                case PROCEEDING:
                    result = sysGroupingModule.getCompetitors() > sysGroupingModule.getRecords();
                    break;
                default:
                    break;
            }
        }
        return result;
    }
}
