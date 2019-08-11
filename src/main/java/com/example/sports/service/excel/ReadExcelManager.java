package com.example.sports.service.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.example.sports.mapper.SysProjectSignMapper;
import com.example.sports.model.SysGroupingModule;
import com.example.sports.model.SysProjectSign;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-08-05 23:11
 **/
@Component
public class ReadExcelManager {

    @Autowired
    private SysProjectSignMapper sysProjectSignMapper;

    //sheet名匹配
    private static final String SUFFIX = "场地";
    // 总行数
    private int totalRows = 0;

    /**
     * 读EXCEL文件，获取信息集合
     *
     * @param mFile
     * @return
     */
    public boolean getExcelInfo(MultipartFile mFile) {
        // 获取文件名
        String fileName = mFile.getOriginalFilename();
        try {
            // 验证文件名是否合格
            if (!validateExcel(fileName)) {
                return false;
            }
            // 根据文件名判断文件是2003版本还是2007版本
            boolean isExcel2003 = true;
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            return createExcel(mFile.getInputStream(), isExcel2003);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据excel里面的内容读取客户信息
     *
     * @param is      输入流
     * @param isExcel2003   excel是2003还是2007版本
     * @return
     * @throws IOException
     */
    public boolean createExcel(InputStream is, boolean isExcel2003) {
        try {
            Workbook wb = null;
            // 当excel是2003时,创建excel2003
            if (isExcel2003) {
                wb = new HSSFWorkbook(is);
            }
            // 当excel是2007时,创建excel2007
            else {
                wb = new XSSFWorkbook(is);
            }
            // 读取Excel里面客户的信息
            return readExcelValue(wb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 读取Excel里面客户的信息
     *
     * @param wb
     * @return
     */
    private boolean readExcelValue(Workbook wb) {
        Iterator<Sheet> sheetIter = wb.sheetIterator();
        while(sheetIter.hasNext()){
            Sheet sheet = sheetIter.next();
            String sheetName = sheet.getSheetName();
            if(!StringUtils.endsWith(sheetName, SUFFIX)){
                continue;
            }
            // 得到Excel的行数
            this.totalRows = sheet.getPhysicalNumberOfRows();
            if(totalRows <= 0){
                continue;
            }
            String place = null;
            List<SysProjectSign> sysProjectSignList = new ArrayList<>();
            for(int i = 0; i < totalRows; i++){
                Row row = sheet.getRow(i);
                if(row == null){
                    continue;
                }
                Cell cell = row.getCell(1);
                if(cell == null){
                    continue;
                }
                if(CellType.BLANK.equals(cell.getCellType())){
                    continue;
                }
                if(CellType.STRING.equals(cell.getCellType())){
                    String[] infos = cell.getStringCellValue().split(" ");
                    place = infos[0];
                }else if(CellType.NUMERIC.equals(cell.getCellType())){
                    SysProjectSign sysProjectSign = buildSysProjectSign(row, place, null);
                    sysProjectSignList.add(sysProjectSign);
                }
                if(sysProjectSignList.size() >= 10){
                    sysProjectSignMapper.batchInsert(sysProjectSignList);
                    sysProjectSignList.clear();
                }
            }
        }
        return true;
    }

    /**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return
     */
    private boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            return false;
        }
        return true;
    }

    // @描述：是否是2003的excel，返回true是2003
    private static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    // @描述：是否是2007的excel，返回true是2007
    private static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$") || filePath.matches("^.+\\.(?i)(xlsm)$");
    }

    private SysProjectSign buildSysProjectSign(Row row, String place, Map<String, String> ext){
        long timestamp = System.currentTimeMillis();
        SysProjectSign sysProjectSign = new SysProjectSign();
        sysProjectSign.setCreateTime(timestamp);
        sysProjectSign.setUpdateTime(timestamp);
        sysProjectSign.setUsername(row.getCell(2).getStringCellValue());
        sysProjectSign.setSysUserSid(row.getCell(3).getStringCellValue());
        sysProjectSign.setGroupName(row.getCell(4).getStringCellValue());
        sysProjectSign.setTeamType(row.getCell(5).getStringCellValue());
        sysProjectSign.setProjectId(String.valueOf((int)row.getCell(6).getNumericCellValue()));
        sysProjectSign.setPlace(place);
        if(ext != null){
            sysProjectSign.setExt(JSON.toJSONString(ext));
        }
        return sysProjectSign;
    }

    /*private SysGroupingModule buildGroupingModule(String[] infos){
        long timestamp = System.currentTimeMillis();
        SysGroupingModule sysGroupingModule = new SysGroupingModule();
        sysGroupingModule.setCreateTime(timestamp);
        sysGroupingModule.setUpdateTime(timestamp);
        //初始化未打印
        sysGroupingModule.setPrinted(0);
        sysGroupingModule.setCompetitionId();
    }*/



}