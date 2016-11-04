package com.hzit.crm.service.impl;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.Module;
import com.hzit.crm.core.entity.Role;
import com.hzit.crm.core.entity.RoleModule;
import com.hzit.crm.core.mapper.RoleMapper;
import com.hzit.crm.service.RoleService;
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
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void save(Role role) {
        int id = roleMapper.selectMaxId();
        role.setRoleId(id+1);
        roleMapper.insertRole(role);
    }

    @Override
    public void update(Role role) {
        roleMapper.updateRole(role);
    }

    /**
     * 获取列表数据
     * @param map
     * @param pageable
     * @return
     */
    @Override
    public DataGrid<Role> roleList(Map<String, String> map,Pageable pageable) {
        DataGrid<Role> dataGrid = new DataGrid<Role>();
        Page<Role> page = roleMapper.searchRoleByParams(map,pageable);
        dataGrid.setTotal(page.getTotalElements());
        dataGrid.setRows(page.getContent());
        return dataGrid;
    }

    @Override
    public List<Role> searchRole(Map<String, String> map) {
        return roleMapper.searchRoleByParams(map);
    }

    @Override
    public List<Role> searchRoleByParams(@Param("map") Map<String, String> map) {
        return roleMapper.searchRoleByParams(null);
    }

    @Override
    public List<RoleModule> findModuleByUserId(String userId) {
        return roleMapper.findModuleByUserId(userId);
    }

    /*@Override
    public List<Module> searchModuleByParams(@Param("map") Map<String, String> map) {
       // return roleMapper.searchRoleByParams(map);
        return null;
    }*/
}
