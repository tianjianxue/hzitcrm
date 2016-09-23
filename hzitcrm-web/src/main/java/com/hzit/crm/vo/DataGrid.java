package com.hzit.crm.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：easyui数据分页
 * Created by clark1230 on 2016/7/16.
 */
public class DataGrid<T> {

    private Long total = 0L;
    private List<T> rows = new ArrayList<T>();

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
