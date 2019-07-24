package com.example.sports.model;

import java.io.Serializable;
import java.util.Date;


/**
 * @author xingchao.lxc
 */
public class SysUser implements Serializable {
    /**  */
    private Long id;

    /** 学号/教师编号 */
    private String sid;

    /** 密码 */
    private String password;

    /** 账号类型1、运动会负责人 2、裁判员 3、普通参赛人员 */
    private Short type;

    /** 头像 */
    private String avatar;

    /** 是否冻结 1、使用 0、冻结 */
    private Boolean frozen;

    /** 创建时间 */
    private Long createTime;

    /** 更新时间 */
    private Long updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Boolean getFrozen() {
        return frozen;
    }

    public void setFrozen(Boolean frozen) {
        this.frozen = frozen;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sid=").append(sid);
        sb.append(", password=").append(password);
        sb.append(", type=").append(type);
        sb.append(", avatar=").append(avatar);
        sb.append(", frozen=").append(frozen);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}