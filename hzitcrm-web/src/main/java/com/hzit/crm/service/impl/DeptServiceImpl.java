package com.hzit.crm.service.impl;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.Dept;
import com.hzit.crm.core.mapper.DeptMapper;
import com.hzit.crm.service.DeptService;
import com.hzit.crm.vo.DataGrid;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by 冼耀基 on 2016/10/24.
 */
@Service
@Transactional
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    /**
     * 添加数据
     * @param dept
     */
    @Override
    public void save(Dept dept) {
        deptMapper.insertDept(dept);
    }

    /**
     * 修改数据
     * @param dept
     */
    @Override
    public void update(Dept dept) {
        deptMapper.updateDept(dept);
    }

    /**
     * 获取部门列表数据
     * @param map
     * @param pageable
     * @return
     */
    @Override
    public DataGrid<Dept> deptList(Map<String, String> map, Pageable pageable) {
        DataGrid<Dept> dataGrid = new DataGrid<Dept>();
        Page<Dept> page = deptMapper.searchDeptByParams(map,pageable);
        dataGrid.setTotal(page.getTotalElements());
        dataGrid.setRows(page.getContent());
        return dataGrid;
    }

    @Override
    public List<Dept> searchDeptByParams(@Param("map") Map<String, String> map) {
        return deptMapper.searchDeptByParams(map);
    }
}
