package com.example.sports.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 分页请求数据
 * 
 *
 * @author jacob
 */
@ApiModel
public class PageRequestBean implements Serializable {

    private static final long   serialVersionUID = -8822923831658955853L;

    protected static final int    DEFAULT_PAGE     = 1;
    protected static final int    DEFAULT_SIZE     = 50;

    /**
     * 当前页，默认1
     */
    @Min(value = 1, message = "当前页码从1开始")
    @ApiModelProperty(value = "当前页，首页为1")
    private int                 pageNum          = DEFAULT_PAGE;

    /**
     * 每页多少条，默认10条
     */
    @Min(value = 1, message = "每页显示条数须大于0")
    @Max(value = 100, message = "每页显示条数须小于100")
    @ApiModelProperty(value = "每页显示条数，须大于0，默认10条")
    private int                 pageSize         = DEFAULT_SIZE;



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


}
