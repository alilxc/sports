package com.example.sports.service.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;


/**
 * @author xingchao.lxc
 */
public class ExportExcel {

    private HSSFWorkbook wb = null;

    private HSSFSheet sheet = null;

    /**
     * @param wb
     * @param sheet
     */
    public ExportExcel(HSSFWorkbook wb, HSSFSheet sheet) {
        super();
        this.wb = wb;
        this.sheet = sheet;
    }

    /**
     * @return the sheet
     */
    public HSSFSheet getSheet() {
        return sheet;
    }

    /**
     * @param sheet
     *            the sheet to set
     */
    public void setSheet(HSSFSheet sheet) {
        this.sheet = sheet;
    }

    /**
     * @return the wb
     */
    public HSSFWorkbook getWb() {
        return wb;
    }

    /**
     * @param wb
     *            the wb to set
     */
    public void setWb(HSSFWorkbook wb) {
        this.wb = wb;
    }

    /**
     * 创建通用EXCEL头部
     *
     * @param headString
     *            头部显示的字符
     *
     * @param rowNum 第几行合并
     * @param colSum
     *            该报表的列数
     */
    public void createNormalHead(String headString, int rowNum,  int colSum) {

        HSSFRow row = sheet.createRow(rowNum);

        // 设置第一行
        HSSFCell cell = row.createCell(0);
        row.setHeight((short) 400);

        // 定义单元格为字符串类型
        cell.setCellType(CellType.STRING);
        cell.setCellValue(new HSSFRichTextString(headString));

        // 指定合并区域
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, colSum));

        HSSFCellStyle cellStyle = wb.createCellStyle();
        // 指定单元格居中对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 指定单元格垂直居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 指定单元格自动换行
        cellStyle.setWrapText(true);

        // 设置单元格字体
        HSSFFont font = wb.createFont();
        font.setBold(true);
        font.setFontName("宋体");
        font.setFontHeight((short) 300);
        cellStyle.setFont(font);

        cell.setCellStyle(cellStyle);
    }


    /**
     * 设置报表标题
     *
     * @param columHeader
     *            标题字符串数组
     */
    public void createColumHeader(String[] columHeader) {

        // 设置列头
        HSSFRow row2 = sheet.createRow(2);

        // 指定行高
        row2.setHeight((short) 600);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 指定单元格居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 指定单元格垂直居中对齐
        cellStyle.setWrapText(true);// 指定单元格自动换行

        // 单元格字体
        HSSFFont font = wb.createFont();
        font.setBold(true);
        font.setFontName("宋体");
        font.setFontHeight((short) 250);
        cellStyle.setFont(font);

        /*
         * cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单无格的边框为粗体
         * cellStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
         * cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
         * cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
         * cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
         * cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
         * cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
         * cellStyle.setTopBorderColor(HSSFColor.BLACK.index);
         */

        // 设置单元格背景色
        cellStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.index);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        HSSFCell cell3 = null;

        for (int i = 0; i < columHeader.length; i++) {
            cell3 = row2.createCell(i);
            cell3.setCellType(CellType.STRING);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(new HSSFRichTextString(columHeader[i]));
        }

    }

    /**
     * 创建内容单元格
     *
     * @param wb
     *            HSSFWorkbook
     * @param row
     *            HSSFRow
     * @param col
     *            short型的列索引
     * @param align
     *            对齐方式
     * @param val
     *            列值
     */
    public void cteateCell(HSSFWorkbook wb, HSSFRow row, int col, HorizontalAlignment align,
                           String val) {
        HSSFCell cell = row.createCell(col);
        cell.setCellType(CellType.STRING);
        cell.setCellValue(new HSSFRichTextString(val));
        HSSFCellStyle cellstyle = wb.createCellStyle();
        cellstyle.setAlignment(align);
        cell.setCellStyle(cellstyle);
    }

    /**
     * 创建合计行
     *
     * @param colSum
     *            需要合并到的列索引
     * @param cellValue
     */
    public void createLastSumRow(int colSum, String[] cellValue) {

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 指定单元格居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 指定单元格垂直居中对齐
        cellStyle.setWrapText(true);// 指定单元格自动换行

        // 单元格字体
        HSSFFont font = wb.createFont();
        font.setBold(true);
        font.setFontName("宋体");
        font.setFontHeight((short) 250);
        cellStyle.setFont(font);

        HSSFRow lastRow = sheet.createRow((short) (sheet.getLastRowNum() + 1));
        HSSFCell sumCell = lastRow.createCell(0);

        sumCell.setCellValue(new HSSFRichTextString("合计"));
        sumCell.setCellStyle(cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(sheet.getLastRowNum(), (short) 0,
                sheet.getLastRowNum(), (short) colSum));// 指定合并区域

        for (int i = 2; i < (cellValue.length + 2); i++) {
            sumCell = lastRow.createCell(i);
            sumCell.setCellStyle(cellStyle);
            sumCell.setCellValue(new HSSFRichTextString(cellValue[i - 2]));

        }

    }

    /**
     * 输入EXCEL文件
     *
     * @param fileName
     *            文件名
     */
    public void outputExcel(String fileName) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(fileName));
            wb.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}