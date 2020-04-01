package com.example.sports.manager;

import com.example.sports.mapper.SysGroupingModuleMapper;
import com.example.sports.mapper.SysProjectMapper;
import com.example.sports.model.SysGroupingModule;
import com.example.sports.model.SysGroupingModuleExample;
import com.example.sports.model.SysProject;
import com.example.sports.model.SysProjectExample;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-08-31 22:16
 **/
@Component
public class ProjectManager {

    private static final Logger log = LoggerFactory.getLogger(ProjectManager.class);

    /**
     * 比赛和项目的映射关系
     */
    private Map<Integer, Set<String>> competitionAndProject = new ConcurrentHashMap<>();

    /**
     * 项目和分组的映射关系
     */
    private Map<String, Set<String>> projectAndTeam = new ConcurrentHashMap<>();

    /**
     * 项目名称映射关系
     */
    private Map<String, String> projectName = new ConcurrentHashMap<>();

    @Autowired
    private SysProjectMapper sysProjectMapper;

    @Autowired
    private SysGroupingModuleMapper sysGroupingModuleMapper;

    private static final int pageSize = 30;


    @PostConstruct
    public void init(){
        try{
            load();
        }catch (Exception e){
            log.error("[ProjectManager].init failed!", e);
        }
    }

    public void load(){
        SysProjectExample example = new SysProjectExample();
        SysProjectExample.Criteria criteria = example.createCriteria();
        criteria.addActiveFinish(System.currentTimeMillis());
        List<SysProject> competitions = sysProjectMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(competitions)){
            competitions.forEach(competition -> {
                int competitionId = competition.getId().intValue();
                if(!competitionAndProject.containsKey(competitionId)){
                    competitionAndProject.put(competitionId, new HashSet<>());
                }
                long id = 0;
                while(true){
                    List<SysGroupingModule> groupingModules = sysGroupingModuleMapper.selectByPrimaryKey(competitionId,
                                                                id, pageSize);
                    if(CollectionUtils.isNotEmpty(groupingModules)){
                        for(SysGroupingModule groupingModule : groupingModules){
                            competitionAndProject.get(competitionId).add(groupingModule.getProjectId());
                            String key = competitionId + "|" + groupingModule.getProjectId();
                            if(!projectAndTeam.containsKey(key)){
                                projectAndTeam.put(key, new HashSet<>());
                            }
                            projectAndTeam.get(key).add(groupingModule.getTeamType());
                            id = groupingModule.getId() > id ? groupingModule.getId() : id;
                            if(!projectName.containsKey(key)){
                                projectName.put(key, groupingModule.getProjectName());
                            }
                        }
                    }
                    if(groupingModules == null || groupingModules.size() < pageSize){
                        break;
                    }
                }
            });
        }
    }


    public void addProjectTeam(String[] infos){
        if(infos.length == 3){
            Integer competitionId = Integer.valueOf(infos[0]);
            String projectId = infos[1];
            String teamType = infos[2];
            if(!competitionAndProject.containsKey(competitionId)){
                competitionAndProject.put(competitionId, new HashSet<>());
            }
            competitionAndProject.get(competitionId).add(projectId);
            String key = competitionId + "|" + projectId;
            if(!projectAndTeam.containsKey(key)){
                projectAndTeam.put(key, new HashSet<>());
            }
            projectAndTeam.get(key).add(teamType);
        }
    }

    public Set<String> getProjects(Integer competitionId){
        if(competitionAndProject.containsKey(competitionId)){
            return competitionAndProject.get(competitionId);
        }
        return Collections.emptySet();
    }


    public Set<String> getTeamTypeList(Integer competitionId, String projectId){
        String key = competitionId + "|" + projectId;
        if(projectAndTeam.containsKey(key)){
            return projectAndTeam.get(key);
        }
        return Collections.emptySet();
    }

    public String getProjectName(Integer competitionId, String projectId){
        String key = competitionId + "|" + projectId;
        if(projectName.containsKey(key)){
            return projectName.get(key);
        }
        return "未定义项目名";
    }
}
