package com.hzit.crm.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzit.crm.core.entity.RoleModule;
import org.apache.ibatis.annotations.Param;
import com.hzit.crm.core.entity.Role;
import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;

public interface RoleMapper {

	void insertRole(Role role);

	void deleteRoleByRoleId(Integer roleId);

	void updateRole(Role role);

	Page<Role> searchRoleByParams(@Param("map") Map<String, String> map, Pageable pageable);

	List<Role> searchRoleByParams(@Param("map") Map<String, String> map);

	List<RoleModule> findModuleByUserId(String userId);
	/**
	 * 获取id的最大值
	 * @return
     */
	Integer selectMaxId();
} 
