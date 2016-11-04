package com.hzit.crm.vo;

/**
 * Created by 冼耀基 on 2016/11/1.
 */
public class EasyuiEntity {
    private String page;
    private String rows;
    private String sort;
    private String order;
    public EasyuiEntity(){

    }
    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "EasyuiEntity{" +
                "page='" + page + '\'' +
                ", rows='" + rows + '\'' +
                ", sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
