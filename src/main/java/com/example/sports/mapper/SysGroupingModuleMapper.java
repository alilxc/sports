package com.example.sports.mapper;

import com.example.sports.model.SysGroupingModule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-08-08 00:38
 **/
public interface SysGroupingModuleMapper {

    int insert(SysGroupingModule module);

    SysGroupingModule select(@Param("competitionId") int competitionId, @Param("projectId") String projectId,
                             @Param("teamType") String teamType);

    int update(@Param("module") SysGroupingModule module);


}
