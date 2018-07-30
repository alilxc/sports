package com.example.sports.dto;

import java.util.List;

/**
 * @title 分页结果<br/>
 * 构造本结果，请使用PagedResultBuilder类进行构造
 * @Author huangjiarui
 * @date: 2018-03-13
 */
public class PagedResult<E> extends Result<List<E>>{
    /**
     * 是否计算了总和
     */
    private boolean countTotal = false;

    /**
     * 是否查询了全部
     */
    private boolean fetchAll = false;

    /**
     * 总条数
     */
    private Integer total;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 当前页数
     */
    private int currentPage;

    /**
     * 当前分页大小
     */
    private int pageSize;

    /**
     * 构建请使用PagedResult.Builder构建
     */
    public PagedResult(){

    }

    public boolean isCountTotal() {
        return countTotal;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("PagedResult");
        builder.append("[");
        builder.append("countTotal=").append(countTotal).append(",");
        builder.append("fetchAll=").append(fetchAll).append(",");
        builder.append("total=").append(total).append(",");
        builder.append("totalPage=").append(totalPage).append(",");
        builder.append("currentPage=").append(currentPage).append(",");
        builder.append("pageSize=").append(pageSize);
        builder.append("]");

        return builder.toString();
    }

    public static class Builder<E> {

        private PagedResult<E> pagedResult;

        public static <E> Builder<E> from(PagedRequest<E> request){
            Builder<E> builder = new Builder<E>();
            int currentPage = request.getCurrentPage();
            int pageSize = request.getPageSize();
            if(currentPage <= 0){
                currentPage = PagedRequest.DEFAULT_CURRENT_PAGE;
            }
            if(pageSize <= 0){
                pageSize = PagedRequest.DEFAULT_PAGE_SIZE;
            }
            builder.pageSize(pageSize).currentPage(currentPage);
            builder.countTotal(request.isCountTotal());
            builder.fetchAll(request.isFetchAll());
            return builder;
        }

        public Builder(){
            this.pagedResult = new PagedResult<E>();
        }

        public Builder(int currentPage){
            if(currentPage < 1){
                throw new RuntimeException("currentPage error:"+currentPage);
            }
            this.pagedResult = new PagedResult<E>();
            this.pagedResult.currentPage = currentPage;
        }

        public Builder(int currentPage,int pageSize){
            if(currentPage < 1){
                throw new RuntimeException("currentPage error:"+currentPage);
            }

            if(pageSize < 1){
                throw new RuntimeException("pageSize error:"+pageSize);
            }

            this.pagedResult = new PagedResult<E>();
            this.pagedResult.currentPage = currentPage;
            this.pagedResult.pageSize = pageSize;
        }

        public Builder<E> total(int total){
            if(total < 0){
                throw new RuntimeException("total error:"+total);
            }
            this.pagedResult.total = total;
            return this;
        }

        public Builder<E> currentPage(int currentPage){
            if(currentPage < 1){
                throw new RuntimeException("currentPage error:"+currentPage);
            }
            this.pagedResult.currentPage = currentPage;
            return this;
        }

        public Builder<E> pageSize(int pageSize){
            if(pageSize < 1){
                throw new RuntimeException("pageSize error:"+pageSize);
            }

            this.pagedResult.pageSize = pageSize;
            return this;
        }

        public Builder<E> countTotal(boolean countTotal){
            this.pagedResult.countTotal = countTotal;
            return this;
        }

        public Builder<E> fetchAll(boolean fetchAll){
            this.pagedResult.fetchAll = fetchAll;
            return this;
        }

        public Builder<E> data(List<E> data){
            this.pagedResult.data(data);
            return this;
        }

        public Builder<E> code(String code){
            this.pagedResult.code(code);
            return this;
        }

        public Builder<E> description(String description){
            this.pagedResult.description(description);
            return this;
        }


        public PagedResult<E> buildForFailed(String description){
            this.pagedResult.currentPage = 0;
            this.pagedResult.totalPage = 0;
            this.pagedResult.total = 0;
            this.pagedResult.pageSize = 0;
            this.pagedResult.fail("ERROR", description);

            return pagedResult;
        }

        public PagedResult<E> buildForFailed(String code,String description){
            this.pagedResult.currentPage = 0;
            this.pagedResult.totalPage = 0;
            this.pagedResult.total = 0;
            this.pagedResult.pageSize = 0;
            this.pagedResult.fail(code, description);

            return pagedResult;
        }

        public PagedResult<E> buildForSuccess(List<E> data){
            this.pagedResult.data(data);
            return buildForSuccess();
        }

        public PagedResult<E> buildForSuccess(){

            this.pagedResult.setSuccess(true);

            if(this.pagedResult.getData() == null){
                throw new RuntimeException("pagedResult's data list is null, if you means not found, you should return empty list instead.");
            }

            //如果获取全部结果集
            if(this.pagedResult.fetchAll){
                this.pagedResult.currentPage = 1;
                this.pagedResult.totalPage = 1;
                this.pagedResult.total = this.pagedResult.getData().size();
                this.pagedResult.pageSize = this.pagedResult.total;

                return this.pagedResult;
            }


            //计算
            if(this.pagedResult.countTotal || this.pagedResult.total != null){
                this.pagedResult.totalPage = computeTotalPage(this.pagedResult.total,this.pagedResult.pageSize);
            }

            return this.pagedResult;
        }

        private Integer computeTotalPage(Integer total, int pageSize) {
            if(total == null || total < 0){
                throw new RuntimeException("total count illegal:"+total);
            }

            if(pageSize <= 0){
                throw new RuntimeException("pageSize error:"+pageSize);
            }

            int tmp = total / pageSize;
            if(total % pageSize != 0){
                tmp = tmp + 1;
            }

            return tmp;
        }

    }
}
