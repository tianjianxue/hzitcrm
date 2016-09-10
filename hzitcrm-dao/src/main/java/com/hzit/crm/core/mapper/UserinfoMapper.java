package com.hzit.crm.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.hzit.crm.core.entity.Userinfo;
import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;

public interface UserinfoMapper {

	void insertUserinfo(Userinfo userinfo);

	void deleteUserinfoByUserid(Integer userid);

	void updateUserinfo(Userinfo userinfo);

	Page<Userinfo> searchUserinfoByParams(@Param("map") Map<String, String> map, Pageable pageable);

	List<Userinfo> searchUserinfoByParams(@Param("map") Map<String, String> map);

} 
