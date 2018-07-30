package com.example.sports.service;

import com.example.sports.dto.request.LoginRequest;
import com.example.sports.dto.request.RegistRequest;
import com.example.sports.dto.response.LoginRes;
import com.example.sports.model.SysCollege;
import com.example.sports.model.SysUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-04-24
 */
public interface SysService {

    /**
     * 登录
     *
     * @param request
     * @return
     */
    LoginRes login(LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse req);

    /**
     * 注册
     *
     * @param request
     * @return
     */
    void regist(RegistRequest registRequest, HttpServletRequest request, HttpServletResponse req);

    /**
     * 按sid查询学号
     *
     */
    List<SysUser> userInfo(Long sid);
}
