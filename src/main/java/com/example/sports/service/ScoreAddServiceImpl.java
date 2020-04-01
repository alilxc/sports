package com.example.sports.service;

import com.alibaba.fastjson.JSON;
import com.example.sports.dto.request.ScoreAddInfo;
import com.example.sports.dto.response.*;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.File;
import java.util.*;
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

    @Autowired
    private ProjectManager projectManager;

    @Autowired
    private ComplexExportExcelManager exportExcelManager;

    @Autowired
    private SearchService searchService;


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
                                                                                    sysProjectSign.getProjectName(), sysProjectSign.getTeamType());
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
                        sysProjectSign.getProjectName(), sysProjectSign.getTeamType());
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
            int num = sysProjectSignMapper.batchUpdateScore(sysProjectSignList);
            if(num > 0){
                Map<String, List<ScoreAddInfo>> scoreAndInfoMap = scoreAddInfos.stream()
                                                                .collect(Collectors.groupingBy(ScoreAddInfo::router));
                scoreAndInfoMap.forEach((key, values)->{
                    String[] teamAndProject = key.split("#");
                    SysProjectSignExample example = new SysProjectSignExample();
                    SysProjectSignExample.Criteria criteria = example.createCriteria();
                    criteria.andCompetitionIdEqual(competitionId);
                    criteria.andProjectIdEqualTo(teamAndProject[0]);
                    criteria.andTeamType(teamAndProject[1]);
                    criteria.andScoreNotNull();
                    List<SysProjectSign> data = sysProjectSignMapper.selectByExample(example);
                    if(CollectionUtils.isNotEmpty(data)){
                        SysGroupingModule sysGroupingModule = new SysGroupingModule();
                        sysGroupingModule.setUpdateTime(System.currentTimeMillis());
                        sysGroupingModule.setCompetitionId((int)competitionId);
                        sysGroupingModule.setTeamType(teamAndProject[1]);
                        sysGroupingModule.setProjectId(teamAndProject[0]);
                        sysGroupingModule.setRecords(data.size());
                        int updateNum = sysGroupingModuleMapper.update(sysGroupingModule);
                        if(updateNum <= 0){
                            log.info("[ScoreAndService].updateScore affect sysGroupingModule num=0, competitionId={}, place={}, " +
                                            "info={}",
                                    competitionId, place, JSON.toJSONString(sysGroupingModule));
                        }
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

    @Override
    public List<CompetitionRankDTO> rank(Integer competitionId, String projectId) {
        Set<String> teamTypeList = projectManager.getTeamTypeList(competitionId, projectId);
        if(CollectionUtils.isEmpty(teamTypeList)){
            return Collections.emptyList();
        }
        List<CompetitionRankDTO> data = new ArrayList<>();
        SysProjectSignExample example = new SysProjectSignExample();
        for(String teamType : teamTypeList){
            example.clear();
            SysProjectSignExample.Criteria criteria = example.createCriteria();
            criteria.andCompetitionIdEqual(competitionId);
            criteria.andProjectIdEqualTo(projectId);
            criteria.andTeamType(teamType);
            example.setOrderByClause("score desc");
            List<SysProjectSign> projectSigns = sysProjectSignMapper.selectByExample(example);
            if(CollectionUtils.isEmpty(projectSigns)){
                log.info("rank select empty! teamType={}", teamType);
                continue;
            }
            List<UserAchievementDetail> rankResult = rank(projectSigns);
            CompetitionRankDTO competitionRankDTO = new CompetitionRankDTO();
            competitionRankDTO.setTeamType(teamType);
            competitionRankDTO.setAchievementDetailList(rankResult);
            data.add(competitionRankDTO);
        }
        return data;
    }

    @Override
    public File buildCompetitionCandidate(String gameName, int status) {
        try{
            //查询比赛id
            SysProjectExample sysProjectExample = new SysProjectExample();
            SysProjectExample.Criteria projectExampleCriteria = sysProjectExample.createCriteria();
            projectExampleCriteria.andNameEqualTo(gameName);
            List<SysProject> projectList = sysProjectMapper.selectByExample(sysProjectExample);
            if(CollectionUtils.isEmpty(projectList)){
                return null;
            }
            int competitionId = projectList.get(0).getId().intValue();

            Set<String> projectSet = projectManager.getProjects(competitionId);
            List<UserAchievementDetail> individual = Lists.newArrayList();
            List<UserAchievementDetail> team = Lists.newArrayList();
            projectSet.forEach(project -> {
                if(CollectionUtils.isNotEmpty(projectSet)){
                    Set<String> teamTypeList = projectManager.getTeamTypeList(competitionId, project);
                    if(CollectionUtils.isNotEmpty(teamTypeList)){
                        SysProjectSignExample example = new SysProjectSignExample();
                        boolean finish = searchService.judge(competitionId, project, status);
                        if(finish){
                            for(String teamType : teamTypeList) {
                                example.clear();
                                SysProjectSignExample.Criteria criteria = example.createCriteria();
                                criteria.andCompetitionIdEqual(competitionId);
                                criteria.andProjectIdEqualTo(project);
                                criteria.andTeamType(teamType);
                                example.setOrderByClause("score desc");
                                List<SysProjectSign> projectSigns = sysProjectSignMapper.selectByExample(example);
                                if (CollectionUtils.isEmpty(projectSigns)) {
                                    continue;
                                }
                                List<UserAchievementDetail> rankResult = rank(projectSigns);
                                if (isTeam(project)) {
                                    team.addAll(rankResult);
                                } else {
                                    individual.addAll(rankResult);
                                }
                            }
                        }
                    }
                }
            });
            return exportExcelManager.generateExcel(gameName, individual, team);
        }catch (Exception e){

        }
        /*Resource resource = new ClassPathResource("poi/运费模板.xlsx");
        File file = resource.getFile();*/
        return null;
    }

    private List<UserAchievementDetail> rank(List<SysProjectSign> projectSignList){
        List<UserAchievementDetail> userAchievementDetails = new ArrayList<>();
        int rank = 1;
        SysProjectSign sysProjectSign = projectSignList.get(0);
        UserAchievementDetail userAchievementDetail = new UserAchievementDetail(rank, sysProjectSign.getGroupName(),
                                                    sysProjectSign.getSysUserSid(), sysProjectSign.getUsername(),
                                                    sysProjectSign.getScore(), sysProjectSign.getTeamType());
        userAchievementDetail.setProjectId(sysProjectSign.getProjectId());
        userAchievementDetail.setProjectName(sysProjectSign.getProjectName());
        double preScore = userAchievementDetail.getScore();
        userAchievementDetails.add(userAchievementDetail);
        for(int i = 1; i < projectSignList.size(); i++){
            sysProjectSign = projectSignList.get(i);
            if(sysProjectSign.getScore() < preScore){
                rank++;
                if(rank >= 9){
                    break;
                }
            }
            userAchievementDetail = new UserAchievementDetail(rank, sysProjectSign.getGroupName(),
                    sysProjectSign.getSysUserSid(), sysProjectSign.getUsername(),
                    sysProjectSign.getScore(), sysProjectSign.getTeamType());
            userAchievementDetail.setProjectId(sysProjectSign.getProjectId());
            userAchievementDetail.setProjectName(sysProjectSign.getProjectName());
            preScore = userAchievementDetail.getScore();
            userAchievementDetails.add(userAchievementDetail);
        }
        return userAchievementDetails;
    }


    //todo 需要加载项目，并且判断是否是团赛
    private boolean isTeam(String projectId){
        Integer id = Integer.valueOf(projectId);
        if(id >= 7100){
            return true;
        }
        return false;
    }
}
