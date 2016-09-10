package com.hzit.crm.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzit.crm.core.entity.UserInfo1;
import org.apache.ibatis.annotations.Param;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;

public interface UserInfoMapper1 {

	void insertUserinfo(UserInfo1 userinfo);

	void deleteUserinfoByUserid(Integer userid);

	void updateUserinfo(UserInfo1 userinfo);

	Page<UserInfo1> searchUserinfoByParams(@Param("map") Map<String, String> map, Pageable pageable);

	List<UserInfo1> searchUserinfoByParams(@Param("map") Map<String, String> map);

} 
