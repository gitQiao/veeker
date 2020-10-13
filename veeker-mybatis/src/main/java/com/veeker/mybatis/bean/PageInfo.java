package com.veeker.mybatis.bean;

import java.util.List;

/**
 * @author ：qiaoliang
 */
public class PageInfo {
    /** 每页显示的行数*/
    private int pageSize = 5;

    /** 当前页码数（默认给1）*/
    private int pageNumber = 1;

    private List<String> orders;

    private PageInfo() {
    }

    public PageInfo(int pageSize, int pageNumber, List<String> orders) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.orders = orders;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<String> getOrders() {
        return orders;
    }

    public void setOrders(List<String> orders) {
        this.orders = orders;
    }

}
