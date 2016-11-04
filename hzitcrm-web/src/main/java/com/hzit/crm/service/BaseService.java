package com.hzit.crm.service;

import com.hzit.crm.vo.DataGrid;

/**
 * Created by 冼耀基 on 2016/10/24.
 */
public interface BaseService<T> {
    /**
     * 添加
     * @param t
     */
    void save(T t);

    /**
     * 修改
     * @param t
     */
    void update(T t);

    void delete(T t);

}
