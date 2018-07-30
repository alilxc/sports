package com.example.sports.mapper;

import com.example.sports.model.SysProject;
import com.example.sports.model.SysProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysProjectMapper {
    int countByExample(SysProjectExample example);

    int deleteByExample(SysProjectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysProject record);

    int insertSelective(SysProject record);

    List<SysProject> selectByExample(SysProjectExample example);

    SysProject selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysProject record, @Param("example") SysProjectExample example);

    int updateByExample(@Param("record") SysProject record, @Param("example") SysProjectExample example);

    int updateByPrimaryKeySelective(SysProject record);

    int updateByPrimaryKey(SysProject record);
}