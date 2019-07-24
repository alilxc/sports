package com.example.sports.service;

import com.example.sports.constant.ErrorContants;
import com.example.sports.constant.Errors;
import com.example.sports.constant.SysUserEnum;
import com.example.sports.dto.request.LoginRequest;
import com.example.sports.dto.request.RegistRequest;
import com.example.sports.dto.response.LoginRes;
import com.example.sports.dto.response.ScoreSerchRes;
import com.example.sports.exception.BusinessException;
import com.example.sports.mapper.SysUserMapper;
import com.example.sports.mapper.SysUserModuleMapper;
import com.example.sports.mapper.SysUserPtMapper;
import com.example.sports.mapper.SysUserStudentMapper;
import com.example.sports.model.*;
import com.example.sports.service.Session.SessionService;
import com.example.sports.util.Md5Util;
import com.example.sports.util.PasswordUtil;
import com.example.sports.util.SysUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author xingchao.lxc
 */
@Service
public class SysServiceImpl implements SysService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserStudentMapper sysUserStudentMapper;

    @Autowired
    private SysUserModuleMapper sysUserModuleMapper;

    @Autowired
    private SysUserPtMapper sysUserPtMapper;

    @Override
    public LoginRes login(LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse req) {
        SysUser sysUser = SysUtil.getSysUserBySid(loginRequest.getSid());
        if (sysUser == null) {
            throw new BusinessException(ErrorContants.NO_AVALIABLE_DATA, "查不到此用户信息");
        }
        if (!PasswordUtil.encode(loginRequest.getPassword()).equalsIgnoreCase(sysUser.getPassword())) {
            throw new BusinessException(ErrorContants.PARAMS_INAVAILABLE, "账号或密码错误");
        }
        if (sysUser.getFrozen()) {
            throw new BusinessException(ErrorContants.PERMISSION_DENY, "您的账号被冻结，请联系管理员");
        }
        sessionService.setManager(request, sysUser);
        LoginRes loginRes = new LoginRes();
        loginRes.setId(sysUser.getId().intValue());
        loginRes.setType(sysUser.getType());
        loginRes.setSid(sysUser.getSid());
        loginRes.setAvatar(sysUser.getAvatar());

        List<Integer> roleId = new ArrayList<>();
        if(sysUser.getType() == SysUserEnum.ADMIN.getType()){
            loginRes.setRoles(SysUserEnum.ADMIN.getRoles());
        }else if(sysUser.getType() == SysUserEnum.LEADER.getType()){
            loginRes.setRoles(SysUserEnum.LEADER.getRoles());
        }else{
            loginRes.setRoles(SysUserEnum.REFEREE.getRoles());
        }

        return loginRes;

        //查userPt表
        /*SysUserPtExample userPtExample = new SysUserPtExample();
        SysUserPtExample.Criteria criteria = userPtExample.createCriteria();
        criteria.andSysUserIdEqualTo(sysUser.getId().intValue());

        List<SysUserPt> sysUserPts = sysUserPtMapper.selectByExample(userPtExample);
        if (sysUserPts.size() > 0) {
            SysUserPt sysUserPt = sysUserPts.get(0);
            String str = sysUserPt.getMark();
            List<Integer> roleId = new ArrayList<>();


                String[] str2 = str.split(" ");
                if (str != null && str.length() != 0) {
                    for (String list : str2) {
                        Integer listlong = Integer.parseInt(list);
                        roleId.add(listlong);
                    }
                    System.out.println("roledId:" + roleId.toString());
                }

            System.out.println("mark:" + sysUserPt.getMark());

            loginRes.setRoles(roleId);


            return loginRes;
        }
            return null;*/
    }


    @Override
    public void regist(RegistRequest registRequest, HttpServletRequest request, HttpServletResponse req) {
        SysUser sysUser = SysUtil.getSysUserBySid(registRequest.getSid());
        if (sysUser != null) {
            throw new BusinessException(ErrorContants.PARAMS_INAVAILABLE, "此用户已注册！");
        }
        if (sysUser == null) {
            try {
                SysUser sysUser1 = new SysUser();
                long timestamp = System.currentTimeMillis();
                sysUser1.setSid(registRequest.getSid());
                registRequest.setType((short) 3);
                if (registRequest.getType() != 3) {
                    throw new BusinessException(ErrorContants.PARAMS_INAVAILABLE, "❌！");
                }
                sysUser1.setType(registRequest.getType());
                if (registRequest.getAvatar() != null) {
                    sysUser1.setAvatar(registRequest.getAvatar());
                }
                if (!PasswordUtil.match(registRequest.getPassword(), PasswordUtil.encode(registRequest.getRePassword()))) {
                    throw new BusinessException(ErrorContants.PARAMS_INAVAILABLE, "❌！");
                }
                sysUser1.setPassword(PasswordUtil.encode(registRequest.getPassword()));
                sysUser1.setCreateTime(timestamp);
                sysUser1.setUpdateTime(timestamp);
                sysUser1.setFrozen(false);
                sysUserMapper.insertSelective(sysUser1);//创建用户账号

                /*SysUserStudent sysUserStudent = new SysUserStudent();
                sysUserStudent.setSysUserSid(registRequest.getSid());
                sysUserStudent.setSysCollege(registRequest.getCollegeId());

                sysUserStudentMapper.insertSelective(sysUserStudent);//创建学生账号

                //查user获取id
                //查userPt表
                SysUserExample userExample = new SysUserExample();
                SysUserExample.Criteria criteria = userExample.createCriteria();
                criteria.andSidEqualTo(registRequest.getSid());
                List<SysUser> sysUsers = sysUserMapper.selectByExample(userExample);
                if (sysUsers.size()>0)
                {
                    SysUser sysUser2 =sysUsers.get(0);

                    SysUserPt sysUserPt = new SysUserPt();
                    sysUserPt.setSysUserId(sysUser2.getId().intValue());

                    List<Integer> roleId = new ArrayList<>();
                    roleId.add(2);
                    sysUserPt.setMark(String.valueOf(roleId.get(0)));
                    sysUserPt.setRoleName("成绩查询");
                    sysUserPtMapper.insertSelective(sysUserPt);
                }*/





//                req.sendRedirect("/static/loginH.html");
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException(Errors.SYSTEM_REQUEST_PARAM_ERROR, "请输入正确的注册信息");
            }
        }
    }

    @Override
    public List<SysUser> userInfo(Long sid) {
        if (sid != null && sid > 0) {
            //查userstudent表取到collegeid和integra
            SysUserStudentExample studentExample = new SysUserStudentExample();
            SysUserStudentExample.Criteria criteriaS = studentExample.createCriteria();
            criteriaS.andSysUserSidEqualTo(String.valueOf(sid));
            List<SysUserStudent> sysUserStudents = sysUserStudentMapper.selectByExample(studentExample);
            SysUserStudent sysUserStudent =null;

            //查sys_user表取name
            SysUserExample userExample = new SysUserExample();
            SysUserExample.Criteria criteria1 = userExample.createCriteria();
            criteria1.andSidEqualTo(String.valueOf(sid));
            List<SysUser> sysUsers = sysUserMapper.selectByExample(userExample);
            SysUser sysUser = null;
            if (sysUsers.size() >0 && sysUserStudents.size() >0)
            {
                sysUser = sysUsers.get(0);
                System.out.println(sysUser.toString());
                sysUserStudent = sysUserStudents.get(0);
                System.out.println(sysUserStudents.toString());

                List<SysUser> res = new ArrayList<>();

                res.add(sysUser);

                return res;
            }
        }
        return null;
    }
}
