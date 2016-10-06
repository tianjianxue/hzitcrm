package com.hzit.crm.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzit.crm.core.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;

public interface UserInfoMapper {

	void insertUserinfo(UserInfo userinfo);

	void deleteUserinfoByUserid(Integer userid);

	void updateUserinfo(UserInfo userinfo);

	Page<UserInfo> searchUserinfoByParams(@Param("map") Map<String, String> map, Pageable pageable);

	List<UserInfo> searchUserinfoByParams(@Param("map") Map<String, String> map);
	List<UserInfo> findAll();
} 
