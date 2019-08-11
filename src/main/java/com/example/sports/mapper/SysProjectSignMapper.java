package com.example.sports.mapper;

import com.example.sports.model.SysProjectSign;
import com.example.sports.model.SysProjectSignExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author xingchao.lxc
 */
public interface SysProjectSignMapper {
    /*int countByExample(SysProjectSignExample example);

    int deleteByExample(SysProjectSignExample example);

    int deleteByPrimaryKey(Long id);*/

    int insert(SysProjectSign record);

    int insertSelective(SysProjectSign record);

    int batchInsert(@Param("records") List<SysProjectSign> records);

    /*List<SysProjectSign> selectByExample(SysProjectSignExample example);

    SysProjectSign selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysProjectSign record, @Param("example") SysProjectSignExample example);

    int updateByExample(@Param("record") SysProjectSign record, @Param("example") SysProjectSignExample example);

    int updateByPrimaryKeySelective(SysProjectSign record);

    int updateByPrimaryKey(SysProjectSign record);*/
}