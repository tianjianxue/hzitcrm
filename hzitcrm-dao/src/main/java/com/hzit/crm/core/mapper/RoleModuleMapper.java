package com.hzit.crm.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.hzit.crm.core.entity.RoleModule;
import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;

public interface RoleModuleMapper {

	void insertRoleModule(RoleModule roleModule);

	void deleteRoleModuleById(Integer id);

	void updateRoleModule(RoleModule roleModule);

	Page<RoleModule> searchRoleModuleByParams(@Param("map") Map<String, String> map, Pageable pageable);

	List<RoleModule> searchRoleModuleByParams(@Param("map") Map<String, String> map);
	void deleteRoleModuleByRoleId(Integer id);
} 
