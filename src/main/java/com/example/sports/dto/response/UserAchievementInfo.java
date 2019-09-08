package com.example.sports.dto.response;

/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-08-24 22:25
 **/
public class UserAchievementInfo {

    /**
     * 本组顺序
     */
    private long id;

    /** 参赛编号 */
    private String sysUserSid;

    /**
     * 姓名
     */
    private String username;

    /** 个人成绩 */
    private Double  score;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSysUserSid() {
        return sysUserSid;
    }

    public void setSysUserSid(String sysUserSid) {
        this.sysUserSid = sysUserSid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public UserAchievementInfo(String sysUserSid, String userName, double score){
        this.score = score;
        this.sysUserSid = sysUserSid;
        this.username = userName;
    }

    public UserAchievementInfo(){}
}
