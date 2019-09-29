package com.example.sports.service.excel;

import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.example.sports.dto.response.GroupingProjectInfoDTO;
import com.example.sports.dto.response.UserAchievementDetail;
import com.example.sports.manager.ProjectManager;
import com.example.sports.mapper.SysProjectSignMapper;
import com.example.sports.model.SysProjectSign;
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

    private static final String[] titles = {"序号","单位名称","姓名","名次","成绩","项目号","项目名", "组别"};

    private static final int colSize = 8;

    public File generateExcel(String gameName, List<UserAchievementDetail> individual, List<UserAchievementDetail> team){

        HSSFWorkbook wb = new HSSFWorkbook();
        AtomicInteger curRow = new AtomicInteger(0);

        HSSFSheet individualSheet = wb.createSheet("单练发放单");
        //HSSFSheet teamSheet = wb.createSheet("集体发放单");

        ExportExcel exportExcel = new ExportExcel(wb, individualSheet);

        // 给工作表列定义列宽(实际应用自己更改列数)
        for (int i = 0; i < colSize; i++) {
            individualSheet.setColumnWidth(i, 3000);
        }
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
        //font.setBold(true);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);

        // 创建报表头部
        exportExcel.createNormalHead(gameName, curRow.getAndIncrement(),  colSize);

        if(CollectionUtils.isNotEmpty(individual)){
            Map<String, List<UserAchievementDetail>> userAchievementMap = individual.stream().
                    collect(Collectors.groupingBy(UserAchievementDetail::getGroupName));
            userAchievementMap.forEach((groupName, userAchievementDetailList) -> {
                // 设置第二行
                exportExcel.createNormalHead(groupName, curRow.getAndIncrement(), colSize);

                // 设置列头
                HSSFRow row2 = individualSheet.createRow(curRow.getAndIncrement());
                for(int i = 0; i < colSize; i++){
                    HSSFCell cell = row2.createCell(i);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(new HSSFRichTextString(titles[i]));
                }
                int[] prize = new int[3];
                Arrays.fill(prize, 0);
                userAchievementDetailList.forEach(userAchievementDetail -> {
                    HSSFRow row = individualSheet.createRow(curRow.getAndIncrement());
                    exportExcel
                            .cteateCell(wb, row, 0,
                                    HorizontalAlignment.CENTER,userAchievementDetail.getSysUserSid());
                    exportExcel
                            .cteateCell(wb, row, 1,
                                    HorizontalAlignment.CENTER,userAchievementDetail.getGroupName());
                    exportExcel
                            .cteateCell(wb, row, 2,
                                    HorizontalAlignment.CENTER,userAchievementDetail.getUsername());
                    int rank = userAchievementDetail.getRank();
                    if(rank <= 3){
                        prize[rank - 1]++;
                    }
                    exportExcel
                            .cteateCell(wb, row, 3,
                                    HorizontalAlignment.CENTER, String.valueOf(rank));
                    exportExcel
                            .cteateCell(wb, row, 4,
                                    HorizontalAlignment.CENTER, String.valueOf(userAchievementDetail.getScore()));
                    exportExcel
                            .cteateCell(wb, row, 5,
                                    HorizontalAlignment.CENTER,userAchievementDetail.getProjectId());
                    exportExcel
                            .cteateCell(wb, row, 6,
                                    HorizontalAlignment.CENTER,userAchievementDetail.getProjectName());
                    exportExcel
                            .cteateCell(wb, row, 7,
                                    HorizontalAlignment.CENTER,userAchievementDetail.getTeamType());
                });
                //浪费多余的一行
                individualSheet.createRow(curRow.getAndIncrement());

                HSSFRow row = individualSheet.createRow(curRow.getAndIncrement());
                exportExcel
                        .cteateCell(wb, row, 1,
                                HorizontalAlignment.CENTER, "奖牌累计：");
                exportExcel
                        .cteateCell(wb, row, 2,
                                HorizontalAlignment.CENTER, String.valueOf(prize[0] + prize[1] + prize[2]));
                exportExcel
                        .cteateCell(wb, row, 3,
                                HorizontalAlignment.CENTER, "其中:");
                exportExcel
                        .cteateCell(wb, row, 4,
                                HorizontalAlignment.CENTER, String.valueOf(prize[0]) + "块金牌");
                exportExcel
                        .cteateCell(wb, row, 5,
                                HorizontalAlignment.CENTER, String.valueOf(prize[1]) + "块银牌");
                exportExcel
                        .cteateCell(wb, row, 6,
                                HorizontalAlignment.CENTER, String.valueOf(prize[2]) + "块铜牌");

                row = individualSheet.createRow(curRow.getAndIncrement());
                exportExcel
                        .cteateCell(wb, row, 1,
                                HorizontalAlignment.CENTER, "奖状累计：");
                exportExcel
                        .cteateCell(wb, row, 2,
                                HorizontalAlignment.CENTER, String.valueOf(userAchievementDetailList.size()));

                //浪费多余的两行
                individualSheet.createRow(curRow.getAndIncrement());
                individualSheet.createRow(curRow.getAndIncrement());
            });
        }
        return exportExcel.outputExcel("/Users/xingchao.lxc/xingchao/project/sports/测试.xls");
    }
}