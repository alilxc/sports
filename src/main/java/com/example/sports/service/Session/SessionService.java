package com.example.sports.service.Session;


import com.example.sports.model.SysUser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jacob
 */
@Component
public class SessionService {

    private final static String SESSION_USER     = "session_user";
    private final static String SESSION_USER_EXT = "session_user_ext";
    private final static String SESSION_USER_MP  = "session_user_mp";

    /**
     * 获取当前登录后台用户
     * 
     * @param request
     * @return
     */
    public SysUser getManager(HttpServletRequest request) {
        return (SysUser) request.getSession().getAttribute(SESSION_USER);
    }

    public SysUser getUser(HttpServletRequest request) {
        return getManager(request);
    }

    public void setManager(HttpServletRequest request, SysUser sysUser) {
        request.getSession().setAttribute(SESSION_USER, sysUser);
        // request.getSession().setAttribute(SESSION_USER_EXT, SysUtil.getUserExetnd(sysUser));
    }


    public void delete(HttpServletRequest request) {
        request.getSession().removeAttribute(SESSION_USER);
    }

    public Object getUserExt(HttpServletRequest request) {
        return request.getSession().getAttribute(SESSION_USER_EXT);
    }


    public void logout(HttpServletRequest request) {
        request.getSession().removeAttribute(SESSION_USER);
    }

}
