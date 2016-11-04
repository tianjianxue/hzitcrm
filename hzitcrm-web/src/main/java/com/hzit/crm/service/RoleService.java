package com.hzit.crm.service;

import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.Role;
import com.hzit.crm.core.entity.RoleModule;
import com.hzit.crm.vo.DataGrid;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by 冼耀基 on 2016/10/24.
 */
public interface RoleService {
    void save(Role role);
    void update(Role role);
    DataGrid<Role> roleList(Map<String,String> map, Pageable pageable);
    List<Role> searchRole(Map<String,String> map);
   List<Role> searchRoleByParams(@Param("map") Map<String, String> map);
    //List<Module> searchModuleByParams(@Param("map") Map<String, String> map);

    /**
     * 根据用户id查找该用户所能访问的资源
     * @param userId
     * @return
     */
    List<RoleModule> findModuleByUserId(String userId);
}
