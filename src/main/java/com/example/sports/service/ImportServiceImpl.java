package com.example.sports.service;

import com.example.sports.manager.ProjectManager;
import com.example.sports.service.excel.ReadExcelManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-08-05 22:46
 **/
@Service
public class ImportServiceImpl implements ImportService{

    @Autowired
    private ReadExcelManager readExcelManager;

    @Autowired
    private ProjectManager projectManager;

    @Override
    public boolean readExcelFile(MultipartFile file, String gameName) {
        //解析excel，获取上传的事件单
        boolean readResult = readExcelManager.getExcelInfo(file, gameName);
        /*if(readResult){
            //初始化加载项目相关的内容到内存
            projectManager.init();
        }*/
        return readResult;
    }
}
