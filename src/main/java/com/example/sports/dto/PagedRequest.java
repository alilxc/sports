package com.example.sports.dto;

import java.util.List;

/**
 * @title 分页查询请求
 * @Author huangjiarui
 * @date: 2018-03-13
 */
public class PagedRequest<E> extends Result<List<E>>{
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int DEFAULT_CURRENT_PAGE = 1;

    /**
     * 当前页,默认第一页
     */
    private int currentPage = DEFAULT_CURRENT_PAGE;

    /**
     * 当前页大小，默认20条
     */
    private int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 是否查询全部，此参数为true，则会忽略page和pageSize参数
     */
    private boolean fetchAll = false;

    /**
     * 是否执行count计算
     */
    private boolean countTotal = false;

    public void setPageParam(int currentPage,int pageSize,boolean fetchAll,boolean countTotal){
        setCurrentPage(currentPage);
        setPageSize(pageSize);
        this.fetchAll = fetchAll;
        this.countTotal = countTotal;
    }

    public void setPageParam(int currentPage,int pageSize){
        setPageParam(currentPage,pageSize,false,false);
    }

    public void setPageParam(int currentPage,int pageSize,boolean countTotal){
        setPageParam(currentPage,pageSize,false,countTotal);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = resolveCurrentPage(currentPage);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = resolvePageSize(pageSize);
    }

    public boolean isFetchAll() {
        return fetchAll;
    }

    public void setFetchAll(boolean fetchAll) {
        this.fetchAll = fetchAll;
    }

    public boolean isCountTotal() {
        return countTotal;
    }

    public void setCountTotal(boolean countTotal) {
        this.countTotal = countTotal;
    }

    public int resolvePageSize(int pageSize){
        if(pageSize <=0 ){
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    public int resolveCurrentPage(int currentPage){
        if(currentPage <=0){
            currentPage = 1;
        }
        return currentPage;
    }

    public int getMySQLOffset(){
        return (currentPage-1)*pageSize;
    }

}
