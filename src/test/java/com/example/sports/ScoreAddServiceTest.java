package com.example.sports;

import com.alibaba.fastjson.JSON;
import com.example.sports.controller.StatusSerchController;
import com.example.sports.dto.request.ScoreAddInfo;
import com.example.sports.dto.response.CompetitionResultDTO;
import com.example.sports.dto.response.PageInfo;
import com.example.sports.model.SysGroupingModule;
import com.example.sports.service.ScoreAddService;
import com.example.sports.util.ResponseObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-08-27 10:34
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreAddServiceTest {

    @Autowired
    private ScoreAddService scoreAddService;

    @Autowired
    private StatusSerchController statusSerchController;

    @Test
    public void addScoreTest(){
        long competitionId = 15;
        String place = "1-1";
        List<ScoreAddInfo> scoreAddInfoList = new ArrayList<>();
        ScoreAddInfo scoreAddInfo1 = new ScoreAddInfo();
        scoreAddInfo1.setGroupName("诸暨市武术协会代表团");
        scoreAddInfo1.setScore("1.23");
        scoreAddInfo1.setSysProjectId("1203");
        scoreAddInfo1.setTeam(false);
        scoreAddInfo1.setTeamType("FD1");
        scoreAddInfo1.setId("9");

        ScoreAddInfo scoreAddInfo2 = new ScoreAddInfo();
        scoreAddInfo2.setGroupName("诸暨市武术协会代表团");
        scoreAddInfo2.setScore("3.23");
        scoreAddInfo2.setSysProjectId("1203");
        scoreAddInfo2.setTeam(false);
        scoreAddInfo2.setTeamType("FD1");
        scoreAddInfo2.setId("11");

        scoreAddInfoList.add(scoreAddInfo1);
        scoreAddInfoList.add(scoreAddInfo2);
        scoreAddService.addScore(competitionId, place, scoreAddInfoList);
    }

    @Test
    public void comptetitionStatusTest(){
        String gameName = "测试比赛003";
        String status = "1";
        int pageSize = 20;
        int pageNum = 1;
        ResponseObject<PageInfo<CompetitionResultDTO>> result = statusSerchController.comptetitionStatus(gameName, status, pageNum, pageSize);
        System.out.println(JSON.toJSONString(result.getData()));
    }
}
