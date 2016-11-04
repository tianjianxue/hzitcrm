package com.hzit.crm.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.hzit.crm.core.entity.Classinfo;
import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;

public interface ClassinfoMapper {

	Page<Classinfo> searchClassinfoByParams(@Param("map") Map<String, String> map, Pageable pageable);

	List<Classinfo> searchClassinfoByParams(@Param("map") Map<String, String> map);

} 
