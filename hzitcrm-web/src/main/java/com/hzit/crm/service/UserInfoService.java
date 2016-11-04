package com.hzit.crm.service;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.core.entity.UserInfo;
import com.hzit.crm.vo.CustomerInfoVo;
import com.hzit.crm.vo.DataGrid;
import com.hzit.crm.vo.UserInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by 冼耀基 on 2016/9/20.
 */
public interface UserInfoService {
    void save(UserInfo userInfo) throws Exception;
    void update(UserInfo userInfo);
    /**
     * 查找所有的咨询师信息
     * @return
     */
    public List<UserInfo> findAll();

    /**
     * 获取当前咨询师的客户信息
     * @return
     */
    public DataGrid<CustomerInfoVo> customerInfoList(Map<String,String> map, Pageable pageable);
    public List<UserInfo> searchCustomerInfoByParams(Map<String,String> map);

    DataGrid<UserInfoVo> userInfoList(Map<String,String> map,Pageable pageable);
    List<UserInfo> searchUserInfoByRoleAndCompany(@Param("map")Map<String,String> map);
    /**
     * 员工信息模糊查询
     * @param map
     * @return
     */
    DataGrid<UserInfoVo> searchUserInfoByParamsLike(@Param("map") Map<String,String> map,Pageable pageable);

    /**
     * 获取咨询，创量和会销相关人员信息
     * @return
     */
    DataGrid<UserInfoVo> thirdPartUserInfo(@Param("map")Map<String,String> map,Pageable pageable);

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
