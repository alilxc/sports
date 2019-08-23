package com.example.sports.service.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import com.example.sports.dto.response.ProjectRes;
import com.example.sports.mapper.SysCompetitionGroupMapper;
import com.example.sports.mapper.SysGroupingModuleMapper;
import com.example.sports.mapper.SysProjectMapper;
import com.example.sports.mapper.SysProjectSignMapper;
import com.example.sports.model.*;
import com.github.pagehelper.PageHelper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-08-05 23:11
 **/
@Component
public class ReadExcelManager {

    private static final Logger log = LoggerFactory.getLogger(ReadExcelManager.class);

    @Autowired
    private SysProjectSignMapper sysProjectSignMapper;

    @Autowired
    private SysProjectMapper sysProjectMapper;

    @Autowired
    private SysGroupingModuleMapper sysGroupingModuleMapper;

    @Autowired
    private SysCompetitionGroupMapper sysCompetitionGroupMapper;

    //sheet名匹配
    private static final String SUFFIX = "场地";
    // 总行数
    private int totalRows = 0;

    private static final int retryTime = 3;

    private Map<String, Integer> localGrouping = new HashMap<>();

    /**
     * 分组
     */
    public LoadingCache<String, Optional<Boolean>> groupingCache = CacheBuilder.newBuilder().refreshAfterWrite(15, TimeUnit.MINUTES)
            //缓存的最大记录数，默认10000条
            .maximumSize(15000)
            .build(new CacheLoader<String, Optional<Boolean>>() {
                @Override
                public Optional<Boolean> load(String key) throws Exception {
                    return Optional.ofNullable(exist(key));
                }
            });


    private Boolean exist(String key){
        try{
            String[] strArr = key.split("#");
            if(strArr.length == 3){
                SysGroupingModule module = sysGroupingModuleMapper.select(Integer.valueOf(strArr[0]), strArr[1], strArr[2]);
                if(module != null){
                    return true;
                }
            }
        }catch(Exception e){
            log.error("select groupingModule exception! key={}", key, e);
        }
        return false;
    }

    /**
     * 读EXCEL文件，获取信息集合
     *
     * @param mFile
     * @return
     */
    public boolean getExcelInfo(MultipartFile mFile, String gameName) {
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
            return createExcel(mFile.getInputStream(), isExcel2003, gameName);
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
    public boolean createExcel(InputStream is, boolean isExcel2003, String gameName) {
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
            return readExcelValue(wb, gameName);
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
    private boolean readExcelValue(Workbook wb, String gameName) {
        //查询赛事
        Long competitionId = getCompetitionId(gameName);
        if(competitionId < 0){
            return false;
        }
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
                    List<String> infos = parserExtInfo(cell.getStringCellValue());
                    if(infos == null || infos.size() <= 0){
                        continue;
                    }
                    place = infos.get(0);
                    //存储比赛分组信息
                    storeCompetitionGroup(competitionId, place);
                }else if(CellType.NUMERIC.equals(cell.getCellType())){
                    SysProjectSign sysProjectSign = buildSysProjectSign(row, competitionId, place, null);
                    sysProjectSignList.add(sysProjectSign);
                    String projectAndTeam = competitionId + "#" + sysProjectSign.getProjectId() + "#" + sysProjectSign.getTeamType();
                    Integer count = 1;
                    if(localGrouping.containsKey(projectAndTeam)){
                        count = localGrouping.get(projectAndTeam);
                    }
                    localGrouping.put(projectAndTeam, count);
                }
                //db持久化报名用户信息
                if(sysProjectSignList.size() >= 10){
                    storeProjectSign(sysProjectSignList);
                    sysProjectSignList.clear();
                }
            }
            if(localGrouping.size() > 0){
                updateGroupingInfo();
            }
        }
        return true;
    }

    private void storeCompetitionGroup(Long competitionId, String place){
        SysCompetitionGroup sysCompetitionGroup = new SysCompetitionGroup();
        long timestamp = System.currentTimeMillis();
        sysCompetitionGroup.setCreateTime(timestamp);
        sysCompetitionGroup.setUpdateTime(timestamp);
        sysCompetitionGroup.setCompetitionId(competitionId.intValue());
        sysCompetitionGroup.setPlace(place);
        sysCompetitionGroup.setExt(null);
        try{
            for(int i = 0; i < retryTime; i++){
                int insertResult = sysCompetitionGroupMapper.insert(sysCompetitionGroup);
                if(insertResult > 0){
                    break;
                }
            }
        }catch (Exception e){
            log.error("insert competitionGroup failed! competitionId={}, place={}", competitionId, place, e);
        }
    }

    private void storeProjectSign(List<SysProjectSign> sysProjectSignList){
        try{
            for(int i = 0; i < retryTime; i++){
                int num = sysProjectSignMapper.batchInsert(sysProjectSignList);
                if(num > 0){
                    break;
                }
            }
        }catch (Exception e){
            log.error("insert sysProject failed! info={}", JSON.toJSONString(sysProjectSignList), e);
        }
    }

    private void updateGroupingInfo(){
        long timestamp = System.currentTimeMillis();
        try{
            for(Map.Entry<String, Integer> enrty : localGrouping.entrySet()){
                String key = enrty.getKey();
                String[] infos = key.split("#");
                int value = enrty.getValue();
                Optional<Boolean> exist = groupingCache.get(key);
                if(exist != null && exist.get()){
                    SysGroupingModule sysGroupingModule = new SysGroupingModule();
                    sysGroupingModule.setUpdateTime(timestamp);
                    sysGroupingModule.setCompetitionId(Integer.valueOf(infos[0]));
                    sysGroupingModule.setProjectId(infos[1]);
                    sysGroupingModule.setTeamType(infos[2]);
                    sysGroupingModule.setCompetitors(value);
                    sysGroupingModuleMapper.update(sysGroupingModule);
                }else{
                    SysGroupingModule sysGroupingModule = new SysGroupingModule();
                    sysGroupingModule.setCreateTime(timestamp);
                    sysGroupingModule.setUpdateTime(timestamp);
                    sysGroupingModule.setCompetitionId(Integer.valueOf(infos[0]));
                    sysGroupingModule.setPrinted(0);
                    sysGroupingModule.setRecords(0);
                    sysGroupingModule.setProjectId(infos[1]);
                    sysGroupingModule.setTeamType(infos[2]);
                    sysGroupingModule.setCompetitors(value);
                    sysGroupingModuleMapper.insert(sysGroupingModule);
                }
            }
        }catch (Exception e){
            log.error("updateGroupingInfo failed! ", e);
        }

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

    private SysProjectSign buildSysProjectSign(Row row, Long competitionId, String place, Map<String, String> ext){
        long timestamp = System.currentTimeMillis();
        SysProjectSign sysProjectSign = new SysProjectSign();
        sysProjectSign.setCreateTime(timestamp);
        sysProjectSign.setUpdateTime(timestamp);
        sysProjectSign.setCompetitionId(competitionId);
        sysProjectSign.setOrderId((int)row.getCell(1).getNumericCellValue());
        sysProjectSign.setUsername(row.getCell(2).getStringCellValue());
        sysProjectSign.setSysUserSid(row.getCell(3).getStringCellValue());
        sysProjectSign.setGroupName(row.getCell(4).getStringCellValue());
        if(CellType.NUMERIC.equals(row.getCell(5).getCellType())){
            sysProjectSign.setTeamType(String.valueOf((int)row.getCell(5).getNumericCellValue()));
        }else{
            sysProjectSign.setTeamType(row.getCell(5).getStringCellValue());
        }
        if(CellType.NUMERIC.equals(row.getCell(6).getCellType())){
            sysProjectSign.setProjectId(String.valueOf((int)row.getCell(6).getNumericCellValue()));
        }else{
            sysProjectSign.setProjectId(row.getCell(6).getStringCellValue());
        }
        sysProjectSign.setPlace(place);
        if(ext != null){
            sysProjectSign.setExt(JSON.toJSONString(ext));
        }
        return sysProjectSign;
    }

    private List<String> parserExtInfo(String info){
        if(StringUtils.isEmpty(info)){
            return Collections.emptyList();
        }
        int index = 0;
        String content = info.trim();
        while(index < content.length()){
            if(content.charAt(index) == '-' || content.charAt(index) <= '9' && content.charAt(index) >= '0'){
                index++;
            }else{
                break;
            }
        }
        List<String> ext = new ArrayList<>();
        String place = info.substring(0, index);
        ext.add(place);
        while(index < content.length() && content.charAt(index) == ' '){
            index++;
        }
        if(index < content.length()){
            ext.add(info.substring(index));
        }
        return ext;
    }

    private SysGroupingModule buildGroupingModule(Long competitionId, List<String> infos){
        long timestamp = System.currentTimeMillis();
        SysGroupingModule sysGroupingModule = new SysGroupingModule();
        sysGroupingModule.setCreateTime(timestamp);
        sysGroupingModule.setUpdateTime(timestamp);
        //初始化未打印
        sysGroupingModule.setPrinted(0);
        sysGroupingModule.setCompetitionId(competitionId.intValue());
        return sysGroupingModule;
    }

    private Long getCompetitionId(String competition){
        SysProjectExample example = new SysProjectExample();
        SysProjectExample.Criteria criteria = example.createCriteria();

        criteria.andNameEqualTo(competition);
        List<SysProject> sysProjects = sysProjectMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(sysProjects)){
            return sysProjects.get(0).getId();
        }
        return -1L;
    }



}