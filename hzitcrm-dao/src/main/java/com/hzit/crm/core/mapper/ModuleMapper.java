package com.hzit.crm.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.hzit.crm.core.entity.Module;
import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;

public interface ModuleMapper {

	void insertModule(Module module);

	void deleteModuleByModuleId(Integer moduleId);

	void updateModule(Module module);

	Page<Module> searchModuleByParams(@Param("map") Map<String, String> map, Pageable pageable);

	List<Module> searchModuleByParams(@Param("map") Map<String, String> map);
	List<Module> searchModuleByWeCharNo(@Param("wechartno") String wechartno);
} 
