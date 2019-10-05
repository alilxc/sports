package com.example.sports.dto.response;

/**
 * @program: sports
 * @description:
 * @author: xingchao.lxc
 * @create: 2019-10-05 21:05
 **/
public class PageInfo<T> {

    private T data;

    private int total;

    private int curPage;

    private int pages;

    private int pageNum;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurPage() {
        return curPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
