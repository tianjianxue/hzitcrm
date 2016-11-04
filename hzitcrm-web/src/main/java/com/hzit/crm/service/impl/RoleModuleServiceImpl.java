package com.hzit.crm.service.impl;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.RoleModule;
import com.hzit.crm.core.mapper.RoleModuleMapper;
import com.hzit.crm.service.RoleModuleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by 冼耀基 on 2016/10/28.
 */
@Service
@Transactional
public class RoleModuleServiceImpl implements RoleModuleService{
    @Autowired
    private RoleModuleMapper roleModuleMapper;
    @Override
    public void insertRoleModule(RoleModule roleModule,String roleId,String moduleId) {
        roleModuleMapper.deleteRoleModuleByRoleId(Integer.parseInt(roleId));
        String[] str = moduleId.split(",");
        for(String result : str){
            roleModule = new RoleModule();
            roleModule.setRoleId(Integer.parseInt(roleId));
            roleModule.setModuleId(Integer.parseInt(result));
            roleModuleMapper.insertRoleModule(roleModule);
        }
    }

    @Override
    public void deleteRoleModuleById(Integer id) {
        roleModuleMapper.deleteRoleModuleById(id);
    }

    @Override
    public void updateRoleModule(RoleModule roleModule) {
        roleModuleMapper.updateRoleModule(roleModule);
    }

    @Override
    public Page<RoleModule> searchRoleModuleByParams(@Param("map") Map<String, String> map, Pageable pageable) {
        return null;
    }

    @Override
    public List<RoleModule> searchRoleModuleByParams(@Param("map") Map<String, String> map) {
        return roleModuleMapper.searchRoleModuleByParams(map);
    }
}
