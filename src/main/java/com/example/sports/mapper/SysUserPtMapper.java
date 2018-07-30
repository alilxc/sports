package com.example.sports.mapper;

import com.example.sports.model.SysUserPt;
import com.example.sports.model.SysUserPtExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserPtMapper {
    int countByExample(SysUserPtExample example);

    int deleteByExample(SysUserPtExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUserPt record);

    int insertSelective(SysUserPt record);

    List<SysUserPt> selectByExample(SysUserPtExample example);

    SysUserPt selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysUserPt record, @Param("example") SysUserPtExample example);

    int updateByExample(@Param("record") SysUserPt record, @Param("example") SysUserPtExample example);

    int updateByPrimaryKeySelective(SysUserPt record);

    int updateByPrimaryKey(SysUserPt record);
}