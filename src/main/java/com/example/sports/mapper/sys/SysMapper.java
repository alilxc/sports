package com.example.sports.mapper.sys;

import com.example.sports.dto.response.SchoolScoreRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @title
 * @Author huangjiarui
 * @date: 2018-05-03
 */
public interface SysMapper {

    /**
     *
     * 查询校级记录前三
     * @param
     * @return
     */
    List<SchoolScoreRes> selectByProjectId(@Param("id") Integer id);

    /**
     *
     * 各学院校级记录前三
     * @param
     * @return
     */
    List<SchoolScoreRes> selectByProject();

}
