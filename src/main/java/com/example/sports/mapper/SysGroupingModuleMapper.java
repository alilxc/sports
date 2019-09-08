package com.example.sports.mapper;

import com.example.sports.model.SysGroupingModule;
import com.example.sports.model.SysGroupingModuleExample;
import com.example.sports.model.SysProjectExample;
import io.swagger.models.auth.In;
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


    List<SysGroupingModule> selectByExample(SysGroupingModuleExample example);

    List<SysGroupingModule> selectByPrimaryKey(@Param("competitionId") int competitionId, @Param("id")Long id,
                                               @Param("pageSize") int pageSize);

}
