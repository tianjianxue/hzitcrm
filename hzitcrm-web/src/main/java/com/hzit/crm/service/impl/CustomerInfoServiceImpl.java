package com.hzit.crm.service.impl;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.core.entity.CustomerState;
import com.hzit.crm.core.entity.UserInfo;
import com.hzit.crm.core.mapper.CustomerInfoMapper;
import com.hzit.crm.core.mapper.CustomerStateMapper;
import com.hzit.crm.core.mapper.UserInfoMapper;
import com.hzit.crm.service.CustomerInfoService;
import com.hzit.crm.vo.CustomerInfoVo;
import com.hzit.crm.vo.DataGrid;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by 冼耀基 on 2016/9/20.
 */
@Service
@Transactional//开启事务
public class CustomerInfoServiceImpl implements CustomerInfoService {
    @Autowired
    private CustomerInfoMapper customerInfoMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private CustomerStateMapper customerStateMapper;


    /**
     * 获取当前日期的来访客户(得到用户的名称和状态)
     * @return
     */
    @Override
    public List<CustomerInfoVo> findByNameAndState() {
        List<CustomerInfoVo> customerInfoVoList =new ArrayList<CustomerInfoVo>();
        List<CustomerInfo> customerInfoList = customerInfoMapper.findByNameAndState();
        CustomerInfoVo customerInfoVo = null;
        Map<String,String> customerStateMap = new HashMap<String, String>();
        if(customerInfoList != null && customerInfoList.size() >0){
            for(CustomerInfo customerInfo : customerInfoList){//遍历客户列表
                customerStateMap = new HashMap<String, String>();
                customerStateMap.put("stateId",customerInfo.getCustomerState()+"");//获取客户状态编号
                customerInfoVo = new CustomerInfoVo();
                BeanUtils.copyProperties(customerInfo,customerInfoVo);
                //获取客户状态
                List<CustomerState> customerStateList = customerStateMapper.searchCustomerStateByParams(customerStateMap);
                if(customerStateList != null && customerStateList.size() >0){
                    customerInfoVo.setCustomerStateMsg(customerStateList.get(0).getCustomerState());//获取客户状态信息
                }
                customerInfoVoList.add(customerInfoVo);
            }
        }
        return customerInfoVoList;
    }

    /**
     * 添加客户信息
     * @param customerInfo
     */
    @Override
    public void insertCustomerInfo(CustomerInfo customerInfo) {
        customerInfoMapper.insertCustomerInfo(customerInfo);
    }

    /**
     * 根据参数查找数据
     * @param map
     * @return
     */
    @Override
    public List<CustomerInfo> searchCustomerInfoByParams(Map<String,String> map){
        return customerInfoMapper.searchCustomerInfoByParams(map);
    }



    /**
     * 保存客户的真实名称和咨询师id
     */
    @Override
    public void insertByRealNameAndUserId(CustomerInfo customerInfo,String userId) {
        //获取员工所在公司id
        Map<String,String> userParamMap = new HashMap<String, String>();
        userParamMap.put("userId",userId);
        List<UserInfo> userInfoList = userInfoMapper.searchUserInfoByParams(userParamMap);
        if(userInfoList != null && userInfoList.size() >0){
            customerInfo.setCompanyId(userInfoList.get(0).getCompanyId());
        }
        customerInfoMapper.insertCustomerInfo(customerInfo);
    }


    /**
     * 录入客户信息
     * @param customerInfo
     */
    @Override
    public void updateCustomerInfo(CustomerInfo customerInfo) {
        customerInfoMapper.updateCustomerInfo(customerInfo);
    }
    /**
     * 模糊查询
     * @param map
     * @param pageable
     * @return
     */
    @Override
    public DataGrid<CustomerInfoVo> searchCustomerInfoByParamsLike(@Param("map") Map<String, String> map, Pageable pageable) {
        Page<CustomerInfo> page = customerInfoMapper.searchCustomerInfoByParamsLike(map,pageable);
        return  common(page);
    }
    /**
     * 获取客户列表
     * @param map
     * @param pageable
     * @return
     */
    @Override
    public DataGrid<CustomerInfoVo> customerInfoList(Map<String, String> map, Pageable pageable) {
        Page<CustomerInfo> page = customerInfoMapper.searchCustomerInfoByParamsLike(map,pageable);
        return common(page);
    }

    private DataGrid<CustomerInfoVo> common(Page<CustomerInfo> page) {
        DataGrid<CustomerInfoVo> dataGrid = new DataGrid<CustomerInfoVo>();
        dataGrid.setTotal(page.getTotalElements());
        List<CustomerInfoVo> customerInfoVos = new ArrayList<CustomerInfoVo>();
        CustomerInfoVo customerInfoVo = null;

        Map<String,String> customerStateMap = null;

        if(page != null){
            for(CustomerInfo customerInfo : page.getContent()){
                customerInfoVo = new CustomerInfoVo();
                BeanUtils.copyProperties(customerInfo,customerInfoVo);
                for(UserInfo userInfo : userInfoMapper.findAll()){
                    customerStateMap  = new HashMap<String, String>();
                    customerStateMap.put("stateId",customerInfo.getCustomerState()+"");
                    List<CustomerState> customerStateList = customerStateMapper.searchCustomerStateByParams(customerStateMap);
                    if(customerStateList != null && customerStateList.size() >0){
                        customerInfoVo.setCustomerStateMsg(customerStateList.get(0).getCustomerState());
                    }
                    if(userInfo.getUserId().equals(customerInfoVo.getUserId())){
                        customerInfoVo.setName(userInfo.getName());
                        customerInfoVo.setUserName(userInfo.getName());
                    }
                }
                customerInfoVos.add(customerInfoVo);//添加客户信息
            }

            dataGrid.setRows(customerInfoVos);
        }
        return dataGrid;
    }

    /**
     * 根据客户id查找客户信息
     * @param customerId
     * @return
     */
    @Override
    public CustomerInfo findCustomerInfoById(String customerId) {
        Map<String ,String> map =  new Hashtable<String, String>();
        map.put("customerId",customerId);
        List<CustomerInfo> list = customerInfoMapper.searchCustomerInfoByParams(map);
        CustomerInfo customerInfo = null;
        if(list != null && list.size() >0){
            customerInfo = list.get(0);
        }
        return customerInfo;
    }

    /**
     * 客户跟进时获取客户相应信息
     * @param userId
     * @return
     */
    @Override
    public List<CustomerInfo> showCustomerTrace(String userId) {
        Map<String,String> map = new HashMap<String, String>();
        map.put("userId",userId);
        return customerInfoMapper.showCustomerTrace(map);
    }

    @Override
    public Page<CustomerInfo> pageCustomerTrace(Map<String,String> map,Pageable pageable) {
        return customerInfoMapper.searchCustomerInfoByParams(map,pageable);
    }

}
