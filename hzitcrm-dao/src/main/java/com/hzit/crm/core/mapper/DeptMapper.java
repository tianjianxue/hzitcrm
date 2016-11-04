package com.hzit.crm.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzit.crm.core.entity.Dept;
import org.apache.ibatis.annotations.Param;
import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;

public interface DeptMapper {

	void insertDept(Dept dept);

	void deleteDeptById(Integer id);

	void updateDept(Dept dept);

	Page<Dept> searchDeptByParams(@Param("map") Map<String, String> map, Pageable pageable);

	List<Dept> searchDeptByParams(@Param("map") Map<String, String> map);

} 
