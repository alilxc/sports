package com.example.sports.dto;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author jacob
 */
@ApiModel
public class PageResponseBean<T> implements Serializable {

    private static final long serialVersionUID = 6891987584786061197L;
    /**
     * 返回数据内容
     */
    @ApiModelProperty(value = "返回数据内容")
    private List<T>           content;
    /**
     * 是否是第一页
     */
    @ApiModelProperty(value = "是否是第一页")
    private boolean           first;

    /**
     * 是否是最后一页
     */
    @ApiModelProperty(value = "是否是最后一页")
    private boolean           last;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private int               pageNum;

    /**
     * 当前页的条目数
     */
    @ApiModelProperty(value = "每页显示条数")
    private int               pageSize;

    /**
     * 总条数
     */
    @ApiModelProperty(value = "总条数")
    private long              totalElements;

    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private int               totalPages;

    public PageResponseBean(PageInfo<T> pageInfo, Page<Object> page) {
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();

        this.last = pageInfo.isIsLastPage();
        this.first = pageInfo.isIsFirstPage();
        this.content = pageInfo.getList();
        this.totalElements = pageInfo.getTotal();
        this.totalPages = pageInfo.getPages();
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isFirst() {
        return pageNum == 1;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
