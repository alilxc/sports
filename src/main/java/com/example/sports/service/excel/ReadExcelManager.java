package com.example.sports.service.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.alibaba.fastjson.JSON;
import com.example.sports.constant.BaseConstants;
import com.example.sports.dto.response.ProjectRes;
import com.example.sports.manager.ProjectManager;
import com.example.sports.mapper.*;
import com.example.sports.model.*;
import com.github.pagehelper.PageHelper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
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

    @Autowired
    private ProjectManager projectManager;

    //sheet名匹配
    private static final String SUFFIX = "场地";

    //是否是团队
    private static final String TEAM = "团";

    // 总行数
    private int totalRows = 0;

    private static final int retryTime = 3;

    private Map<String, Integer> localGrouping = new HashMap<>();

    private Map<String, String> projectNameMap = new HashMap<>();

    /**
     * 用于执行异步文件的读取存储
     */
    private ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 分组
     */
    public LoadingCache<String, Optional<Boolean>> groupingCache = CacheBuilder.newBuilder().refreshAfterWrite(30, TimeUnit.MINUTES)
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
            AtomicBoolean isExcel2003 = new AtomicBoolean(true);
            if (isExcel2007(fileName)) {
                isExcel2003.set(false);
            }
            File uploadFile = storeFile(mFile);
            if(uploadFile != null){
                executorService.submit(() -> {
                    if(createExcel(uploadFile, isExcel2003.get(), gameName)){
                        projectManager.load();
                    }
                });
                return true;
            }
            //return createExcel(mFile.getInputStream(), isExcel2003, gameName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private File storeFile(MultipartFile file){
        //获取上传文件名,包含后缀
        String originalFilename = file.getOriginalFilename();
        //获取后缀
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        //保存的文件名
        String dFileName = UUID.randomUUID()+substring;
        //保存路径
        //springboot 默认情况下只能加载 resource文件夹下静态资源文件
        String path = BaseConstants.DIR_PATH + dFileName;
        //生成保存文件
        File uploadFile = new File(path);
        //将上传文件保存到路径
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return uploadFile;
    }

    /**
     * 根据excel里面的内容读取客户信息
     *
     * @param aFile      输入流文件
     * @param isExcel2003   excel是2003还是2007版本
     * @return
     * @throws IOException
     */
    public boolean createExcel(File aFile, boolean isExcel2003, String gameName) {
        Workbook wb = null;
        try {
            InputStream is = new FileInputStream(aFile);
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
        }finally {
            if(wb != null){
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
            if(!StringUtils.contains(sheetName, SUFFIX)){
                continue;
            }
            // 得到Excel的行数
            this.totalRows = sheet.getPhysicalNumberOfRows();
            if(totalRows <= 0){
                continue;
            }
            String place = null;
            List<SysProjectSign> sysProjectSignList = new ArrayList<>();
            boolean isTeam = false;
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
                    Map<String, Object> ext = new HashMap<>();
                    Cell nextCell = row.getCell(2);
                    if(nextCell != null && CellType.STRING.equals(cell.getCellType())){
                        isTeam = nextCell.getStringCellValue().contains(TEAM);
                    }else{
                        isTeam = false;
                    }
                    if(infos.size() > 1){
                        ext.put("desc", infos.get(1));
                    }
                    ext.put("team", isTeam);
                    //存储比赛分组信息
                    storeCompetitionGroup(competitionId, place, ext);
                }else if(CellType.NUMERIC.equals(cell.getCellType())){
                    SysProjectSign sysProjectSign = buildSysProjectSign(row, competitionId, place, isTeam);
                    sysProjectSignList.add(sysProjectSign);
                    String projectAndTeam = competitionId + "#" + sysProjectSign.getProjectId() + "#" + sysProjectSign.getTeamType();
                    Integer count = 1;
                    if(localGrouping.containsKey(projectAndTeam)){
                        count += localGrouping.get(projectAndTeam);
                    }
                    localGrouping.put(projectAndTeam, count);
                    if(!projectNameMap.containsKey(projectAndTeam)){
                        projectNameMap.put(projectAndTeam, sysProjectSign.getProjectName());
                    }
                }
                //db持久化报名用户信息
                if(sysProjectSignList.size() >= 10){
                    storeProjectSign(sysProjectSignList);
                    sysProjectSignList.clear();
                }
            }
            if(localGrouping.size() > 0){
                updateGroupingInfo();
                localGrouping.clear();
                projectNameMap.clear();

            }
            if(sysProjectSignList.size() > 0){
                storeProjectSign(sysProjectSignList);
                sysProjectSignList.clear();
            }
        }
        return true;
    }

    private void storeCompetitionGroup(Long competitionId, String place, Map<String, Object> ext){
        SysCompetitionGroup sysCompetitionGroup = new SysCompetitionGroup();
        long timestamp = System.currentTimeMillis();
        sysCompetitionGroup.setCreateTime(timestamp);
        sysCompetitionGroup.setUpdateTime(timestamp);
        sysCompetitionGroup.setCompetitionId(competitionId.intValue());
        sysCompetitionGroup.setPlace(place);
        sysCompetitionGroup.setExt(JSON.toJSONString(ext));
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
            insertOrUpdate(sysProjectSignList);
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
                if(exist.isPresent() && exist.get()){
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
                    sysGroupingModule.setProjectName(projectNameMap.get(key));
                    sysGroupingModule.setTeamType(infos[2]);
                    sysGroupingModule.setCompetitors(value);
                    sysGroupingModuleMapper.insert(sysGroupingModule);
                }
                //缓存中暂存项目列表
                projectManager.addProjectTeam(infos);
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

    private SysProjectSign buildSysProjectSign(Row row, Long competitionId, String place, boolean isTeam){
        long timestamp = System.currentTimeMillis();
        SysProjectSign sysProjectSign = new SysProjectSign();
        sysProjectSign.setCreateTime(timestamp);
        sysProjectSign.setUpdateTime(timestamp);
        sysProjectSign.setCompetitionId(competitionId);
        sysProjectSign.setOrderId((int)row.getCell(1).getNumericCellValue());
        sysProjectSign.setUsername(row.getCell(2).getStringCellValue());
        if(!isTeam){
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
            if(CellType.NUMERIC.equals(row.getCell(7).getCellType())){
                sysProjectSign.setProjectName(String.valueOf((int)row.getCell(7).getNumericCellValue()));
            }else{
                sysProjectSign.setProjectName(row.getCell(7).getStringCellValue());
            }
        }else{
            sysProjectSign.setGroupName(row.getCell(3).getStringCellValue());
            if(CellType.NUMERIC.equals(row.getCell(4).getCellType())){
                sysProjectSign.setTeamType(String.valueOf((int)row.getCell(5).getNumericCellValue()));
            }else{
                sysProjectSign.setTeamType(row.getCell(4).getStringCellValue());
            }
            if(CellType.NUMERIC.equals(row.getCell(5).getCellType())){
                sysProjectSign.setProjectId(String.valueOf((int)row.getCell(5).getNumericCellValue()));
            }else{
                sysProjectSign.setProjectId(row.getCell(5).getStringCellValue());
            }
            if(CellType.NUMERIC.equals(row.getCell(6).getCellType())){
                sysProjectSign.setProjectName(String.valueOf((int)row.getCell(6).getNumericCellValue()));
            }else{
                sysProjectSign.setProjectName(row.getCell(6).getStringCellValue());
            }
        }

        sysProjectSign.setPlace(place);
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

    private boolean insertOrUpdate(List<SysProjectSign> sysProjectSignList){
        if(CollectionUtils.isEmpty(sysProjectSignList)){
            return true;
        }
        int num = 0;
        try{
            num = sysProjectSignMapper.batchUpdate(sysProjectSignList);
        }catch (Exception e){
            log.error("insertOrUpdate sysProjectSign failed! info={}", JSON.toJSONString(sysProjectSignList), e);
        }
        if(num > 0){
            return true;
        }
        //process exception
        sysProjectSignList.forEach(sysProjectSign -> {
            SysProjectSignExample example = new SysProjectSignExample();
            SysProjectSignExample.Criteria criteria = example.createCriteria();
            criteria.andCompetitionIdEqual(sysProjectSign.getCompetitionId());
            criteria.addGroupName(sysProjectSign.getGroupName());
            criteria.andSysUserName(sysProjectSign.getUsername());
            List<SysProjectSign> sysProjectSigns = sysProjectSignMapper.selectByExample(example);
            if(CollectionUtils.isNotEmpty(sysProjectSigns)){
                sysProjectSignMapper.batchUpdate(Lists.newArrayList(sysProjectSign));
            }else{
                sysProjectSignMapper.insert(sysProjectSign);
            }
        });
        return true;

    }





}