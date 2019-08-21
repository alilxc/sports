package com.example.sports.model;

import java.io.Serializable;

/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-08-21 01:50
 **/
public class SysCompetitionGroup implements Serializable{

    private static final long serialVersionUID = -1;

    private Long id;

    private Long createTime;

    /** 更新时间 */
    private Long updateTime;

    /**
     * 赛事id
     */
    private Integer competitionId;

    /**
     * 场次分组
     */
    private String place;

    /**
     * 扩展字段
     */
    private String ext;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
