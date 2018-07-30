package com.example.sports.mapper;

import com.example.sports.model.SysGradingModule;
import com.example.sports.model.SysGradingModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysGradingModuleMapper {
    int countByExample(SysGradingModuleExample example);

    int deleteByExample(SysGradingModuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysGradingModule record);

    int insertSelective(SysGradingModule record);

    List<SysGradingModule> selectByExample(SysGradingModuleExample example);

    SysGradingModule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysGradingModule record, @Param("example") SysGradingModuleExample example);

    int updateByExample(@Param("record") SysGradingModule record, @Param("example") SysGradingModuleExample example);

    int updateByPrimaryKeySelective(SysGradingModule record);

    int updateByPrimaryKey(SysGradingModule record);
}