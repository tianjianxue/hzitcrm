package com.hzit.crm.core.mapper;

import java.util.List;
import java.util.Map;

import com.hzit.crm.core.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;

public interface UserInfoMapper {
	/**
	 * 插入员工数据
	 * @param userinfo
     */
	void insertUserInfo(UserInfo userinfo);

	/**
	 * 根据员工编号删除数据
	 * @param userid
     */
	void deleteUserinfoByUserid(Integer userid);

	/**
	 * 修改员工数据
	 * @param userinfo
     */
	void updateUserInfo(UserInfo userinfo);

	/**
	 * 根据具体参数查询员工数据(分页)
	 * @param map
	 * @param pageable
     * @return
     */
	Page<UserInfo> searchUserInfoByParams(@Param("map") Map<String, String> map, Pageable pageable);

	List<UserInfo> searchUserInfoByParams(@Param("map") Map<String, String> map);

	/**
	 * 查找所有员工数据
	 * @return
     */
	List<UserInfo> findAll();

	/**
	 * 根据id查找员工
	 * @param map
	 * @return
     */
	List<UserInfo> findUserNameById(@Param("map") Map<String,String> map);

	/**
	 * 获取分公司所有咨询师和咨询主管信息
	 * @param map
	 * @return
     */
	List<UserInfo> searchUserInfoByRoleAndCompany(@Param("map")Map<String,String> map);

	/**
	 * 员工信息模糊查询
	 * @param map
	 * @return
     */
	Page<UserInfo> searchUserInfoByParamsLike(@Param("map")Map<String,String> map,Pageable pageable);

	/**
	 * 获取咨询，创量和会销相关人员信息
	 * @return
     */
	Page<UserInfo> thirdPartUserInfo(@Param("map")Map<String,String> map,Pageable pageable);

	/**
	 * 获取本公司所有邀约人
	 * @return
     */
	List<UserInfo> getAllYaoYueRen(@Param("map")Map<String,String> map);

	/**
	 * 获取本公司所有的咨询师
	 * @param map
	 * @return
     */
	List<UserInfo> consultantList(@Param("map")Map<String,String>  map);
} 
