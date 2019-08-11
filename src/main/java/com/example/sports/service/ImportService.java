package com.example.sports.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-08-05 22:43
 **/
public interface ImportService {

    boolean readExcelFile(MultipartFile file);
}
