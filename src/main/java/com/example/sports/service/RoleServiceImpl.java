package com.example.sports.service;


import com.example.sports.constant.ErrorContants;
import com.example.sports.dto.PageRequestBean;
import com.example.sports.dto.request.RoleModiRequest;
import com.example.sports.dto.response.RoleRes;
import com.example.sports.exception.BusinessException;
import com.example.sports.mapper.SysModuleMapper;
import com.example.sports.mapper.SysUserMapper;
import com.example.sports.mapper.SysUserPtMapper;
import com.example.sports.model.SysModule;
import com.example.sports.model.SysUser;
import com.example.sports.model.SysUserExample;
import com.example.sports.model.SysUserPt;
import com.example.sports.model.SysUserPtExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-04-29
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired//可以用@Resource
    private SysUserPtMapper sysUserPtMapper;//自动注入Mapper

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysModuleMapper sysModuleMapper;

    @Override
    public void addAdmin(RoleModiRequest request) {
        if (request.getsId() != null && StringUtils.isNotBlank(request.getName()) && request.getRoleId().size() != 0) {
            SysUserPt sysUserPt = new SysUserPt();

            //根据sid查询user
            SysUserExample example = new SysUserExample();
            SysUserExample.Criteria criteria = example.createCriteria();
            criteria.andSidEqualTo(String.valueOf(request.getsId()));

            List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
            if (sysUsers.size() > 0)
            {
                SysUser sysUser = sysUsers.get(0);
                sysUserPt.setSysUserId(sysUser.getId().intValue());

                List<Integer> roleId = request.getRoleId();

                String roleName = "";
                String roleId2 = "";
                //这里需要处理字符串
                for (int i = 0; i < roleId.size(); i++) {
                    SysModule sysModule = sysModuleMapper.selectByPrimaryKey(Long.valueOf(roleId.get(i)));
                    roleName += sysModule.getModuleName() + " ";
                    roleId2 += sysModule.getId()+" ";
                    sysUserPt.setRoleName(roleName);
                    sysUserPt.setMark(roleId2);
                }
                System.out.println("roleId2"+roleId2);
                sysUserPtMapper.insertSelective(sysUserPt);
            }

        }
    }

    @Override
    public void modifyAdmin(Long id, String[] roleId, Integer sId) {
        SysUserPt sysUserPt = sysUserPtMapper.selectByPrimaryKey(id);

        //查user表获取userid
        SysUserExample sysUserExample = new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
       criteria.andSidEqualTo(String.valueOf(sId));
       List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);

       if (sysUsers.size()>0 && sysUserPt !=null)
       {
           SysUser sysUser = sysUsers.get(0);
           sysUserPt.setSysUserId(sysUser.getId().intValue());
           System.out.println(roleId.toString());
           String roleName = "";
           String roleId2 = "";
           for (int i = 0; i < roleId.length; i++) {
               SysModule sysModule = sysModuleMapper.selectByPrimaryKey(Long.valueOf(roleId[i]));
               roleName += sysModule.getModuleName() + " ";
               roleId2 += sysModule.getId()+" ";
               sysUserPt.setRoleName(roleName);
               sysUserPt.setMark(roleId2);
           }
           sysUserPtMapper.updateByPrimaryKey(sysUserPt);
       }


    }

    @Override
    public void delectAdmin(Long id) {
        sysUserPtMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<RoleRes> adminRoleList(PageRequestBean requestBean) {//传参为工具类/已内置

        SysUserPtExample example = new SysUserPtExample();
        SysUserPtExample.Criteria criteria = example.createCriteria();

        List<RoleRes> res = new ArrayList<>();//新建实体类List


        PageHelper.startPage(requestBean.getPageNum(), requestBean.getPageSize(), true);//启动分页，接下来第一条为分页对象
        List<SysUserPt> sysUserPtList = sysUserPtMapper.selectByExample(example);//mybatis查询语句

        if (sysUserPtList.size() > 0) {
            PageInfo<RoleRes> resPage = new PageInfo(sysUserPtList);//放入pageInfo工具类中

            for (SysUserPt sysUserPt : sysUserPtList) {//循环List
                RoleRes dto = new RoleRes();
                SysUser sysUser = sysUserMapper.selectByPrimaryKey(Long.valueOf(sysUserPt.getSysUserId()));

               String roleId2 = sysUserPt.getMark();//roleId字段
                List<Integer> roleId = new ArrayList<>();


                String[] str = roleId2.split(" ");
                if (str != null && str.length != 0)
                {
                    for(String list : str){
                        Integer listlong = Integer.parseInt(list);
                        roleId.add(listlong);
                        System.out.print(list + " ");
                }
                }
                System.out.println(roleId.toString());

                if (sysUser != null &&roleId.size()>0) {
                    dto.setId(sysUserPt.getId().intValue());
                    dto.setsId(Integer.valueOf(sysUser.getSid()));
                    dto.setName(sysUser.getName());
                    dto.setRoleName(sysUserPt.getRoleName());
                    dto.setRoleId(roleId);
                    res.add(dto);//放入新建实体类List中

                    System.out.println(dto.toString());
                } else {
                    throw new BusinessException(ErrorContants.NO_PT_USER, "注册表中无此权限用户！");
                }

            }

            PageInfo<RoleRes> pageInfo = new PageInfo(res);//将循环遍历后的新建List中元素放入返回PageInfo中
            pageInfo.setPages(resPage.getPages());//总页数
            pageInfo.setTotal(resPage.getTotal());//总条数
            pageInfo.setPageSize(resPage.getPageSize());//每页大小
            pageInfo.setPageNum(resPage.getPageNum());//当前页
            return pageInfo;//返回分页完成的数据

        }

        else {
            throw  new BusinessException(ErrorContants.NO_PT,"权限用户表为空！");
        }

    }
}
