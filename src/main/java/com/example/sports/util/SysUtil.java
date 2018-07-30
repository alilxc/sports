package com.example.sports.util;


import com.example.sports.mapper.SysUserMapper;
import com.example.sports.model.SysUser;
import com.example.sports.model.SysUserExample;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @title
 * @Author huangjiarui
 * @date: 2018-04-25
 */
public class SysUtil {

    @Autowired
    private static SysUserMapper sysUserMapper;

    static
    {
        if (sysUserMapper == null){
            sysUserMapper = SpringContextUtil.getBean(SysUserMapper.class);
        }

    }

    public static SysUser getSysUserBySid(String sid){
        if (StringUtils.isNotBlank(sid)){
            SysUserExample example = new SysUserExample();
            SysUserExample.Criteria criteria = example.createCriteria();
            criteria.andSidEqualTo(sid);
            List<SysUser> sysUserList = sysUserMapper.selectByExample(example);
            if (sysUserList.size() > 0){
                SysUser sysUser = sysUserList.get(0);
                return sysUser;
            }
        }
        return null;
    }


//    public static <T>T getUserExetnd(Long userId, Short type, Class<T> userClass) {
//        try {
//            Map<String, Object> map = sysMapper.getUserExtend(userId, type);
//            if (map != null) {
//                //T t = userClass.newInstance();
//                T t = JSON.toJavaObject(JSONObject.parseObject(JSON.toJSONString(map)), userClass);
//                //BeanUtils.populate(t, map);
//                return t;
//            }
//        } catch (Exception e) {
//            throw new BusinessException(ErrorContants.SYSTEM_INNER_ERROR, e.getMessage());
//        }
//        return null;
//
//    }
}
