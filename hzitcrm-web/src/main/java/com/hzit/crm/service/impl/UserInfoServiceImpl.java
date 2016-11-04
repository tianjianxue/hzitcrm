package com.hzit.crm.service.impl;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.Company;
import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.core.entity.CustomerState;
import com.hzit.crm.core.entity.UserInfo;
import com.hzit.crm.core.mapper.*;
import com.hzit.crm.service.UserInfoService;
import com.hzit.crm.util.SHAUtil;
import com.hzit.crm.vo.CustomerInfoVo;
import com.hzit.crm.vo.DataGrid;
import com.hzit.crm.vo.UserInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工业务层
 * Created by 冼耀基 on 2016/9/20.
 */
@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService{
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private CustomerInfoMapper customerInfoMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private CustomerStateMapper customerStateMapper;

    @Override
    public void save(UserInfo userInfo) throws Exception {
        userInfo.setPassword(SHAUtil.shaEncode(userInfo.getPassword()));
        userInfoMapper.insertUserInfo(userInfo);
    }

    @Override
    public void update(UserInfo userInfo) {
        userInfoMapper.updateUserInfo(userInfo);
    }

    @Override
    public List<UserInfo> findAll() {
        return userInfoMapper.findAll();
    }

    /**
     * 获取当前咨询师的客户信息
     * @return
     */
    @Override
    public DataGrid<CustomerInfoVo> customerInfoList(Map<String,String> map, Pageable pageable) {
        DataGrid<CustomerInfoVo> dataGrid = new DataGrid<CustomerInfoVo>();
        Page<CustomerInfo> customerInfos = customerInfoMapper.searchCustomerInfoByParamsLike(map,pageable);
        List<CustomerInfoVo> customerInfoVoList = new ArrayList<CustomerInfoVo>();
        CustomerInfoVo customerInfoVo = null;
        Map<String,String> customerStateMap = null;
        Map<String,String> companyParamMap = null;
        for(CustomerInfo customerInfo : customerInfos.getContent()){
            customerInfoVo = new CustomerInfoVo();
            BeanUtils.copyProperties(customerInfo,customerInfoVo);

            customerStateMap  = new HashMap<String, String>();//客户状态
            customerStateMap.put("stateId",customerInfo.getCustomerState()+"");
            List<CustomerState> customerStateList = customerStateMapper.searchCustomerStateByParams(customerStateMap);
            if(customerStateList != null && customerStateList.size() >0){//客户状态
                customerInfoVo.setCustomerStateMsg(customerStateList.get(0).getCustomerState());
            }
            companyParamMap = new HashMap<String, String>();//公司
            companyParamMap.put("companyId",customerInfo.getCompanyId()+"");
            //客户所在报名的公司
            List<Company> companyList = companyMapper.searchCompanyByParams(companyParamMap);
            if(companyList != null && companyList.size() >0){
                customerInfoVo.setCompanyName(companyList.get(0).getCompanyName());//公司名称
            }
            customerInfoVoList.add(customerInfoVo);
        }
        if(customerInfos != null){
            dataGrid.setTotal(customerInfos.getTotalElements());
        }
        dataGrid.setRows(customerInfoVoList);
        return dataGrid;
    }

    @Override
    public List<UserInfo> searchCustomerInfoByParams(Map<String, String> map) {
        return userInfoMapper.searchUserInfoByParams(map);
    }

    /**
     * 获取员工信息
     * @param map
     * @param pageable
     * @return
     */
    @Override
    public DataGrid<UserInfoVo> userInfoList(Map<String, String> map, Pageable pageable) {
        Page<UserInfo> page = userInfoMapper.searchUserInfoByParams(map,pageable);
        return common(page);
    }

    private DataGrid<UserInfoVo> common(Page<UserInfo> page) {
        DataGrid<UserInfoVo> dataGrid = new DataGrid<UserInfoVo>();
        List<UserInfoVo> userInfoVoList = new ArrayList<UserInfoVo>();
        UserInfoVo userInfoVo = null;
        Map<String,String> paramMap = null;
        Map<String,String> paramDeptMap = null;
        Map<String,String> roleParamMap = null;
        for(UserInfo userInfo : page.getContent()){
            paramMap = new HashMap<String, String>();
            paramDeptMap = new HashMap<String, String>();
            paramMap.put("companyId",userInfo.getCompanyId()+"");

            paramDeptMap.put("id",userInfo.getDeptId()+"");

            roleParamMap = new HashMap<String, String>();
            roleParamMap.put("roleId",userInfo.getRoleId()+"");
            userInfoVo = new UserInfoVo();
            BeanUtils.copyProperties(userInfo,userInfoVo);
            //获取公司信息paramDeptMap
            if(companyMapper.searchCompanyByParams(paramMap) != null && companyMapper.searchCompanyByParams(paramMap).size() > 0){
                userInfoVo.setCompanyName(companyMapper.searchCompanyByParams(paramMap).get(0).getCompanyName());
            }
            //获取部门信息
            if(deptMapper.searchDeptByParams(paramDeptMap) != null && deptMapper.searchDeptByParams(paramDeptMap).size()>0){
                userInfoVo.setDeptName(deptMapper.searchDeptByParams(paramDeptMap).get(0).getDeptName());
            }
            //获取角色信息
            if(roleMapper.searchRoleByParams(roleParamMap)!= null && roleMapper.searchRoleByParams(roleParamMap).size() >0){
                userInfoVo.setRoleName(roleMapper.searchRoleByParams(roleParamMap).get(0).getRoleName());
            }
            userInfoVoList.add(userInfoVo);
        }
        dataGrid.setTotal(page.getTotalElements());
        dataGrid.setRows(userInfoVoList);
        return dataGrid;
    }

    /**
     * 获取分公司所有咨询师和咨询主管的信息
     * @param map
     * @return
     */
    @Override
    public List<UserInfo> searchUserInfoByRoleAndCompany(@Param("map") Map<String, String> map) {
        return userInfoMapper.searchUserInfoByRoleAndCompany(map);
    }

    /**
     * 员工信息模糊查询
     * @param map
     * @return
     */
    @Override
    public DataGrid<UserInfoVo> searchUserInfoByParamsLike(@Param("map") Map<String, String> map,Pageable pageable) {
        Page<UserInfo> page = userInfoMapper.searchUserInfoByParamsLike(map,pageable);
        return common(page);
    }

    /**
     * 获取创量,咨询和会销相关人员信息
     * @return
     */
    @Override
    public DataGrid<UserInfoVo> thirdPartUserInfo(@Param("map")Map<String,String> map,Pageable pageable) {
        Page<UserInfo> page = userInfoMapper.thirdPartUserInfo(map,pageable);
        return common(page);
    }

    /**
     * 获取本公司所有邀约人
     * @return
     */
    @Override
    public List<UserInfo> getAllYaoYueRen(@Param("map") Map<String, String> map) {
        return userInfoMapper.getAllYaoYueRen(map);
    }

    /**
     * 获取本公司所有的咨询师
     * @param map
     * @return
     */
    @Override
    public List<UserInfo> consultantList(@Param("map") Map<String, String> map) {
        return userInfoMapper.consultantList(map);
    }

}
