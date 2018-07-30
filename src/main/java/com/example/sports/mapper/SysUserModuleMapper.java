package com.example.sports.mapper;

import com.example.sports.model.SysUserModule;
import com.example.sports.model.SysUserModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserModuleMapper {
    int countByExample(SysUserModuleExample example);

    int deleteByExample(SysUserModuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUserModule record);

    int insertSelective(SysUserModule record);

    List<SysUserModule> selectByExample(SysUserModuleExample example);

    SysUserModule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysUserModule record, @Param("example") SysUserModuleExample example);

    int updateByExample(@Param("record") SysUserModule record, @Param("example") SysUserModuleExample example);

    int updateByPrimaryKeySelective(SysUserModule record);

    int updateByPrimaryKey(SysUserModule record);
}