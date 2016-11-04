package com.hzit.crm.service;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.RoleModule;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by 冼耀基 on 2016/10/28.
 */
public interface RoleModuleService {
    void insertRoleModule(RoleModule roleModule,String roleId,String moduleId);

    void deleteRoleModuleById(Integer id);

    void updateRoleModule(RoleModule roleModule);

    Page<RoleModule> searchRoleModuleByParams(@Param("map") Map<String, String> map, Pageable pageable);

    List<RoleModule> searchRoleModuleByParams(@Param("map") Map<String, String> map);
}
