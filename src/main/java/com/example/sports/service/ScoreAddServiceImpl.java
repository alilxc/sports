package com.example.sports.service;

import com.alibaba.fastjson.JSON;
import com.example.sports.dto.request.ScoreAddInfo;
import com.example.sports.dto.response.SysGroupingDetailDTO;
import com.example.sports.dto.response.SysProjectSignDTO;
import com.example.sports.dto.response.UserAchievementInfo;
import com.example.sports.mapper.*;
import com.example.sports.mapper.sys.SysMapper;
import com.example.sports.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author xingchao.lxc
 */
@Service
public class ScoreAddServiceImpl implements ScoreAddService {

    private static final Logger log = LoggerFactory.getLogger(ScoreAddServiceImpl.class);


    @Autowired
    private SysProjectMapper sysProjectMapper;

    @Autowired
    private SysProjectSignMapper sysProjectSignMapper;

    @Autowired
    private SysCompetitionGroupMapper sysCompetitionGroupMapper;

    @Autowired
    private SysGroupingModuleMapper sysGroupingModuleMapper;


    @Override
    public List<SysProject> sysProject() {
        SysProjectExample example = new SysProjectExample();
        SysProjectExample.Criteria criteria = example.createCriteria();
        List<SysProject> sysProjects = sysProjectMapper.selectByExample(example);
        return sysProjects;
    }


    @Override
    public void addScore(long competitionId, String place, List<ScoreAddInfo> scoreAddInfos) {
        if(scoreAddInfos == null || scoreAddInfos.size() < 0){
            return;
        }
        Map<Boolean, List<ScoreAddInfo>> scoreInfoMap = scoreAddInfos.stream()
                                .collect(Collectors.groupingBy(ScoreAddInfo::isTeam));
        scoreInfoMap.forEach((isTeam, scoreAddInfoList) -> {
            if(isTeam){
                scoreAddInfoList.forEach(scoreAddInfo -> {
                    for(int i = 0; i < 3; i++){
                        if(updateScore(competitionId, place, scoreAddInfo)){
                            break;
                        }
                    }
                });
            }else{
                for(int i = 0; i < 3; i++){
                    if(updateScore(competitionId, place, scoreAddInfoList)){
                        break;
                    }
                }
            }
        });
    }

    @Override
    public SysGroupingDetailDTO groupSignMemberInfo(String gameName, String place) {
        SysGroupingDetailDTO sysGroupingDetailDTO = new SysGroupingDetailDTO();
        try{
            SysProjectExample example = new SysProjectExample();
            SysProjectExample.Criteria criteria = example.createCriteria();
            criteria.andNameEqualTo(gameName);
            List<SysProject> projectList = sysProjectMapper.selectByExample(example);
            if(projectList == null || projectList.size() <= 0){
                sysGroupingDetailDTO.setGroupingMemberDTOList(Collections.emptyList());
            }
            long competitionId = projectList.get(0).getId();
            SysCompetitionGroup competitionGroup = sysCompetitionGroupMapper.selectByPlace(competitionId, place);
            boolean isTeam = false;
            if(competitionGroup.getExt() != null){
                Map<String, Object> ext = JSON.parseObject(competitionGroup.getExt(), Map.class);
                if(ext != null && ext.containsKey("team")){
                    isTeam = (boolean) ext.get("team");
                }
            }
            SysProjectSignExample projectSignExample = new SysProjectSignExample();
            SysProjectSignExample.Criteria projectSignCriteria = projectSignExample.createCriteria();
            projectSignCriteria.andCompetitionIdEqual(competitionId);
            projectSignCriteria.andPlaceEqual(place);
            projectSignExample.setOrderByClause("order_id asc");
            List<SysProjectSign> sysProjectSignList = sysProjectSignMapper.selectByExample(projectSignExample);
            sysGroupingDetailDTO.setGroupingMemberDTOList(convert(sysProjectSignList, isTeam));
            sysGroupingDetailDTO.setTeam(isTeam);
        }catch (Exception e){
            log.error("[ScoreAddService].groupSignMemberInfo exception! gameName={}, place={}", gameName, place, e);
            sysGroupingDetailDTO.setGroupingMemberDTOList(Collections.emptyList());
        }
        return sysGroupingDetailDTO;
    }

    private List<SysProjectSignDTO> convert(List<SysProjectSign> sysProjectSignList, boolean isTeam){
        if(sysProjectSignList == null || sysProjectSignList.size() <= 0){
            return Collections.emptyList();
        }
        List<SysProjectSignDTO> data = new ArrayList<>();
        if(isTeam){
            sysProjectSignList.stream()
                    .collect(Collectors.groupingBy(SysProjectSign::router))
                    .forEach((groupName, memberLists)->{
                        SysProjectSign sysProjectSign = memberLists.get(0);
                        SysProjectSignDTO sysProjectSignDTO = new SysProjectSignDTO(sysProjectSign.getGroupName(), sysProjectSign.getProjectId(),
                                                                                    "XXXXX", sysProjectSign.getTeamType());
                        List<UserAchievementInfo> userAchievementInfoList = new ArrayList<>();
                        for(SysProjectSign sysProjectSign1 : memberLists){
                            userAchievementInfoList.add(convert2UserAchievementInfo(sysProjectSign1));
                        }
                        sysProjectSignDTO.setAchievementInfoList(userAchievementInfoList);
                        data.add(sysProjectSignDTO);
            });
        }else{
            for(SysProjectSign sysProjectSign : sysProjectSignList){
                SysProjectSignDTO sysProjectSignDTO = new SysProjectSignDTO(sysProjectSign.getGroupName(), sysProjectSign.getProjectId(),
                        "XXXXX", sysProjectSign.getTeamType());
                List<UserAchievementInfo> userAchievementInfoList = new ArrayList<>();
                userAchievementInfoList.add(convert2UserAchievementInfo(sysProjectSign));
                sysProjectSignDTO.setAchievementInfoList(userAchievementInfoList);
                data.add(sysProjectSignDTO);
            }
        }
        return data;
    }

    private UserAchievementInfo convert2UserAchievementInfo(SysProjectSign sysProjectSign){
        UserAchievementInfo userAchievementInfo = new UserAchievementInfo();
        userAchievementInfo.setId(sysProjectSign.getOrderId());
        userAchievementInfo.setSysUserSid(sysProjectSign.getSysUserSid());
        userAchievementInfo.setUsername(sysProjectSign.getUsername());
        if(sysProjectSign.getScore() != null){
            userAchievementInfo.setScore(sysProjectSign.getScore());
        }
        return userAchievementInfo;
    }

    @Transactional(rollbackFor=Exception.class)
    public boolean updateScore(long competitionId, String place, ScoreAddInfo scoreAddInfo){
        try{
            //创建更新分数的条件
            SysProjectSignExample sysProjectSignExample = new SysProjectSignExample();
            SysProjectSignExample.Criteria criteria = sysProjectSignExample.createCriteria();
            criteria.andPlaceEqual(place);
            criteria.andCompetitionIdEqual(competitionId);
            criteria.andProjectIdEqualTo(scoreAddInfo.getSysProjectId());
            criteria.andTeamType(scoreAddInfo.getTeamType());
            criteria.addGroupName(scoreAddInfo.getGroupName());

            double score = Double.valueOf(scoreAddInfo.getScore());
            int num = sysProjectSignMapper.updateByExample(score, sysProjectSignExample);
            if(num > 0){
                SysGroupingModule sysGroupingModule = new SysGroupingModule();
                sysGroupingModule.setCompetitionId((int)competitionId);
                sysGroupingModule.setTeamType(scoreAddInfo.getTeamType());
                sysGroupingModule.setProjectId(scoreAddInfo.getSysProjectId());
                sysGroupingModule.setRecords(num);
                int updateNum = sysGroupingModuleMapper.update(sysGroupingModule);
            }
        }catch (Exception e){
            log.error("[ScoreAndService].updateScore failed! competitionId={}, place={}, info={}",
                    competitionId, place, JSON.toJSONString(scoreAddInfo), e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    @Transactional(rollbackFor=Exception.class)
    public boolean updateScore(long competitionId, String place, List<ScoreAddInfo> scoreAddInfos){
        try{
            List<SysProjectSign> sysProjectSignList = new ArrayList<>(scoreAddInfos.size());
            for(ScoreAddInfo scoreAddInfo : scoreAddInfos){
                SysProjectSign sysProjectSign = new SysProjectSign();
                sysProjectSign.setGroupName(scoreAddInfo.getGroupName());
                sysProjectSign.setPlace(place);
                sysProjectSign.setCompetitionId(competitionId);
                sysProjectSign.setOrderId(Integer.valueOf(scoreAddInfo.getId()));
                sysProjectSign.setProjectId(scoreAddInfo.getSysProjectId());
                sysProjectSign.setTeamType(scoreAddInfo.getTeamType());
                sysProjectSign.setScore(Double.valueOf(scoreAddInfo.getScore()));
                sysProjectSignList.add(sysProjectSign);
            }
            int num = sysProjectSignMapper.batchUpdate(sysProjectSignList);
            if(num > 0){
                Map<String, List<ScoreAddInfo>> scoreAndInfoMap = scoreAddInfos.stream()
                                                                .collect(Collectors.groupingBy(ScoreAddInfo::router));
                scoreAndInfoMap.forEach((key, values)->{
                    String[] teamAndProject = key.split("#");
                    SysGroupingModule sysGroupingModule = new SysGroupingModule();
                    sysGroupingModule.setCompetitionId((int)competitionId);
                    sysGroupingModule.setTeamType(teamAndProject[1]);
                    sysGroupingModule.setProjectId(teamAndProject[0]);
                    sysGroupingModule.setRecords(values.size());
                    int updateNum = sysGroupingModuleMapper.update(sysGroupingModule);
                    if(updateNum <= 0){
                        log.info("[ScoreAndService].updateScore affect sysGroupingModule num=0, competitionId={}, place={}, " +
                                        "info={}",
                                competitionId, place, JSON.toJSONString(sysGroupingModule));
                    }
                });
            }else{
                log.info("[ScoreAndService].updateScore affect sysProjectSign num=0, competitionId={}, place={}, info={}",
                        competitionId, place, JSON.toJSONString(sysProjectSignList));
            }
        }catch (Exception e){
            log.error("[ScoreAndService].updateScore failed! competitionId={}, place={}, info={}",
                    competitionId, place, JSON.toJSONString(scoreAddInfos), e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }
}
