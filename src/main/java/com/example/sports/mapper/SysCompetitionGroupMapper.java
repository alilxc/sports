package com.example.sports.mapper;

import com.example.sports.model.SysCompetitionGroup;

import java.util.List;

/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-08-21 01:55
 **/
public interface SysCompetitionGroupMapper {

    int insert(SysCompetitionGroup module);

    /**
     * 根据比赛id获取当前场地分布情况
     */
    List<SysCompetitionGroup> select(int competitionId);
}
