package com.example.sports.mapper;

import com.example.sports.model.SysCollege;
import com.example.sports.model.SysCollegeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysCollegeMapper {
    int countByExample(SysCollegeExample example);

    int deleteByExample(SysCollegeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysCollege record);

    int insertSelective(SysCollege record);

    List<SysCollege> selectByExample(SysCollegeExample example);

    SysCollege selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysCollege record, @Param("example") SysCollegeExample example);

    int updateByExample(@Param("record") SysCollege record, @Param("example") SysCollegeExample example);

    int updateByPrimaryKeySelective(SysCollege record);

    int updateByPrimaryKey(SysCollege record);
}