package com.example.sports.mapper;

import com.example.sports.model.SysUserStudent;
import com.example.sports.model.SysUserStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserStudentMapper {
    int countByExample(SysUserStudentExample example);

    int deleteByExample(SysUserStudentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUserStudent record);

    int insertSelective(SysUserStudent record);

    List<SysUserStudent> selectByExample(SysUserStudentExample example);

    SysUserStudent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysUserStudent record, @Param("example") SysUserStudentExample example);

    int updateByExample(@Param("record") SysUserStudent record, @Param("example") SysUserStudentExample example);

    int updateByPrimaryKeySelective(SysUserStudent record);

    int updateByPrimaryKey(SysUserStudent record);
}