package com.example.sports.interceptor;


import com.alibaba.fastjson.JSON;
import com.example.sports.annotation.Open;

import com.example.sports.constant.Errors;
import com.example.sports.model.SysUser;
import com.example.sports.service.Session.SessionService;

import com.example.sports.util.ResponseObjectUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


/**
 * 登录验证Interceptor
 *
 * @author jacob
 */
@Component
public class LoginValidateInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private SessionService sessionService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        if (handler instanceof HandlerMethod && null != ((HandlerMethod) handler).getMethodAnnotation(Open.class)) {
            return true;
        }
        SysUser user = sessionService.getUser(request);
        if (user == null) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(ResponseObjectUtil.fail(Errors.USER_LOGIN_REQUIRE)));
            writer.flush();
            response.sendRedirect("/login.html");
            return false;
        }
        return true;
    }

}
