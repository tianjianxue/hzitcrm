package com.hzit.crm.service;

import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.Dept;
import com.hzit.crm.vo.DataGrid;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by 冼耀基 on 2016/10/24.
 */
public interface DeptService {
    /**
     * 添加
     * @param dept
     */
    void save(Dept dept);

    /**
     * 修改
     * @param dept
     */
    void update(Dept dept);

    /**
     * 获取部门列表数据
     * @param map
     * @return
     */
    DataGrid<Dept> deptList(Map<String,String> map, Pageable pageable);
    List<Dept> searchDeptByParams(@Param("map") Map<String, String> map);
}
