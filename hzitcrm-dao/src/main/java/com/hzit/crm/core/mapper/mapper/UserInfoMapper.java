package com.hzit.crm.core.mapper.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.hzit.crm.core.mapper.entity.UserInfo;
import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;

public interface UserInfoMapper {

	void insertUserInfo(UserInfo userInfo);

	void deleteUserInfoByUserId(Integer userId);

	void updateUserInfo(UserInfo userInfo);

	Page<UserInfo> searchUserInfoByParams(@Param("map") Map<String, String> map, Pageable pageable);

	List<UserInfo> searchUserInfoByParams(@Param("map") Map<String, String> map);

} 
