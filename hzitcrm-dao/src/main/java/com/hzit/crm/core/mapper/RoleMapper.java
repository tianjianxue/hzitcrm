package com.hzit.crm.core.mapper.mapper;

import java.util.List;
import java.util.Map;

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

} 
