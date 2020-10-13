package com.veeker.mybatis.bean;

import java.util.List;

/**
 * @author ：qiaoliang
 */
public class PageBean<T> extends PageInfo {
    /** 存放  需要显示的 实体类数据*/
    private List<T> lists;
    /** 总页数*/
    private final int totalPage;
    /** 总行数*/
    private final int rows;

    public PageBean(PageInfo pageInfo, Integer rows) {
        super(pageInfo.getPageSize(), pageInfo.getPageNumber(), pageInfo.getOrders());
        this.rows = rows;
        //页数      根据传入的 总行数 以及 每页显示的行数 求出总页数
        this.totalPage=rows % this.getPageSize()==0 ? rows/this.getPageSize() : (rows/this.getPageSize()+1);
        //如果传入的页码为空 或者小于0  就默认给 1
        if (this.getPageNumber() < 0){
            this.setPageNumber(1);
            //如果当前页码数>总页码数    就让 当前页码数 等于 最大页码数
        }
        else if (this.getPageNumber() > this.totalPage && this.totalPage> 0){
            this.setPageNumber(this.totalPage);
        }
    }

    public List<T> getLists() {
        return lists;
    }
    public void setLists(List<T> lists) {
        this.lists = lists;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getRows() {
        return rows;
    }

}
