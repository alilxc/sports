package com.example.sports.mapper;

import com.example.sports.model.SysProjectSign;
import com.example.sports.model.SysProjectSignExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author xingchao.lxc
 */
public interface SysProjectSignMapper {

    int insert(SysProjectSign record);

    int insertSelective(SysProjectSign record);

    int batchInsert(@Param("records") List<SysProjectSign> records);

    List<SysProjectSign> selectByExample(SysProjectSignExample example);

    int updateByExample(@Param("score") double score, @Param("example") SysProjectSignExample example);

    int batchUpdate(@Param("records") List<SysProjectSign> records);
}