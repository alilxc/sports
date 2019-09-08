package com.example.sports.service.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.sports.dto.response.GroupingProjectInfoDTO;
import com.example.sports.manager.ProjectManager;
import com.example.sports.mapper.SysProjectSignMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author xingchao.lxc
 */
@Component
public class ComplexExportExcelManager {

    static HSSFWorkbook wb = new HSSFWorkbook();
    static HSSFSheet sheet = wb.createSheet();

    @Autowired
    private ProjectManager projectManager;

    @Autowired
    private SysProjectSignMapper sysProjectSignMapper;

    private static final String[] titles = {"序号","单位名称","姓名","名次","成绩","项目号","项目名"};

    /*public void generateExcel(int competitionId){
        try{
            Set<String> projectSet = projectManager.getProjects(competitionId);
            if(CollectionUtils.isNotEmpty(projectSet)){
                projectSet.forEach(project -> {
                    Set<String> teamTypeList = projectManager.getTeamTypeList(competitionId, project);
                    if(CollectionUtils.isNotEmpty(teamTypeList)){
                        for(String teamType : teamTypeList){

                        }
                    }
                    boolean find = true;
                    if(CollectionUtils.isNotEmpty(teamTypeList)){
                        for(String teamType : teamTypeList){
                            find = judge(competitionId, project, teamType, status);
                            if(!find){
                                break;
                            }
                        }
                        if(find){
                            //todo 需要设置projectName
                            data.addGroupingProject(new GroupingProjectInfoDTO(project, "XXXXX"));
                        }
                    }
                });
            }
        }


        ExportExcel exportExcel = new ExportExcel(wb, sheet);

        int colSize = titles.length;

        // 给工作表列定义列宽(实际应用自己更改列数)
        for (int i = 0; i < colSize; i++) {
            sheet.setColumnWidth(i, 3000);
        }
        wb.setSheetName(0, "单项奖品发放单");
        // 创建单元格样式
        HSSFCellStyle cellStyle = wb.createCellStyle();

        // 指定单元格居中对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        // 指定单元格垂直居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 指定当单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);

        // 设置单元格字体
        HSSFFont font = wb.createFont();
        font.setBold(true);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);

        // 创建报表头部
        exportExcel.createNormalHead("lxc测试表格", 0,  colSize);

        // 设置第二行
        exportExcel.createNormalHead("ABC队奖品获奖名单", 1, colSize);

        // 设置列头
        HSSFRow row2 = sheet.createRow(2);
        for(int i = 0; i < colSize; i++){
            HSSFCell cell = row2.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(titles[i]));
        }

        for(int i = 3; i < 10; i++){
            HSSFRow row = sheet.createRow(i);
            for (int j = 0; j < colSize; j++) {
                exportExcel
                        .cteateCell(wb, row, (short) j,
                                HorizontalAlignment.CENTER, String
                                        .valueOf(j));
            }
        }
    }*/

    public static void main(String[] args) {

        ExportExcel exportExcel = new ExportExcel(wb, sheet);

        int colSize = titles.length;

        // 给工作表列定义列宽(实际应用自己更改列数)
        for (int i = 0; i < colSize; i++) {
            sheet.setColumnWidth(i, 3000);
        }
        wb.setSheetName(0, "单项奖品发放单");
        // 创建单元格样式
        HSSFCellStyle cellStyle = wb.createCellStyle();

        // 指定单元格居中对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        // 指定单元格垂直居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 指定当单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);

        // 设置单元格字体
        HSSFFont font = wb.createFont();
        font.setBold(true);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);

        // 创建报表头部
        exportExcel.createNormalHead("lxc测试表格", 0,  colSize);

        // 设置第二行
        exportExcel.createNormalHead("ABC队奖品获奖名单", 1, colSize);

        // 设置列头
        HSSFRow row2 = sheet.createRow(2);
        for(int i = 0; i < colSize; i++){
            HSSFCell cell = row2.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(titles[i]));
        }

        for(int i = 3; i < 10; i++){
            HSSFRow row = sheet.createRow(i);
            for (int j = 0; j < colSize; j++) {
                exportExcel
                        .cteateCell(wb, row, (short) j,
                                HorizontalAlignment.CENTER, String
                                        .valueOf(j));
            }
        }


        // 创建最后一行的合计行
        /*String[] cellValue = new String[number - 1];
        for (int i = 0; i < number - 1; i++) {
            cellValue[i] = String.valueOf(i);

        }*/
        //exportExcel.createLastSumRow(1, cellValue);

        exportExcel.outputExcel("/Users/xingchao.lxc/xingchao/project/sports/测试.xls");

    }
}