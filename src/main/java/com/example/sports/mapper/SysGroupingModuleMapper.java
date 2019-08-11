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

    int batchInsert(@Param("groupingModules") List<SysGroupingModule> sysGroupingModuleList);
}
