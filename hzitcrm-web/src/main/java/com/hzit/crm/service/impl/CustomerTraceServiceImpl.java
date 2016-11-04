package com.hzit.crm.service.impl;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.core.entity.CustomerTraceRecord;
import com.hzit.crm.core.entity.UserInfo;
import com.hzit.crm.core.mapper.CustomerTraceRecordMapper;
import com.hzit.crm.core.mapper.UserInfoMapper;
import com.hzit.crm.service.CustomerTraceService;
import com.hzit.crm.vo.CustomerTraceRecordVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 冼耀基 on 2016/10/2.
 */
@Service
@Transactional
public class CustomerTraceServiceImpl implements CustomerTraceService {
    @Autowired
    private CustomerTraceRecordMapper customerTraceRecordMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    /**
     * 根据咨询师id获取客户跟进记录
     * @param userId
     * @return
     */
    @Override
    public List<CustomerTraceRecord> customerTraceByUserId(String userId) {
        Map<String,String> map = new HashMap<String, String>();
        map.put("userId",userId);
        return customerTraceRecordMapper.searchCustomerTraceRecordByParams(map);
    }




    /**
     * 获取跟进记录信息
     * @param map
     * @return
     */
    @Override
    public List<CustomerTraceRecordVo> searchTraceRecord(Map<String, String> map) {
        List<CustomerTraceRecordVo> customerTraceRecordVos = new ArrayList<CustomerTraceRecordVo>();
        CustomerTraceRecordVo customerTraceRecordVo = null;
        Map<String,String> userMap = new HashMap<String, String>();
        userMap.put("userId",map.get("userId"));
        List<UserInfo> userInfos = userInfoMapper.findUserNameById(userMap);
        UserInfo userInfo = null;
        //获取咨询师信息
        if(userInfos != null && userInfos.size() > 0){
           userInfo = userInfos.get(0);
        }
        for(CustomerTraceRecord customerTraceRecord : customerTraceRecordMapper.searchCustomerTraceRecordByParams(map)){
            customerTraceRecordVo = new CustomerTraceRecordVo();
            BeanUtils.copyProperties(customerTraceRecord,customerTraceRecordVo);
            customerTraceRecordVo.setUserName(userInfo.getName());
            customerTraceRecordVos.add(customerTraceRecordVo);
        }
        return customerTraceRecordVos;
    }

    /**
     * 添加跟进记录
     * @param customerTraceRecord
     */
    @Override
    public void addTraceRecord(CustomerTraceRecord customerTraceRecord) {
        customerTraceRecordMapper.insertCustomerTraceRecord(customerTraceRecord);
    }

    /**
     * 获取取跟进记录详情
     * @param customerInfo
     * @return
     */
    @Override
    public List<CustomerTraceRecordVo> detailedCustomerTrace(CustomerInfo customerInfo) {
        //获取跟进记录
        Map<String,String> paramMap = new HashMap<String, String>();
        paramMap.put("customerId",customerInfo.getUserId()+"");
        List<CustomerTraceRecordVo> customerTraceRecordVoList = new ArrayList<CustomerTraceRecordVo>();
        CustomerTraceRecordVo customerTraceRecordVo = null;
        List<CustomerTraceRecord> customerTraceRecordList = customerTraceRecordMapper.searchCustomerTraceRecordByParams(paramMap);
        if(customerTraceRecordList!=null && customerTraceRecordList.size() >0){
            for(CustomerTraceRecord customerTraceRecord : customerTraceRecordList){
                customerTraceRecordVo = new CustomerTraceRecordVo();
                BeanUtils.copyProperties(customerTraceRecord,customerTraceRecordVo);
                BeanUtils.copyProperties(customerTraceRecord,customerTraceRecordVo);
                customerTraceRecordVoList.add(customerTraceRecordVo);
            }
        }
        return customerTraceRecordVoList;
    }

    @Override
    public Page<CustomerTraceRecord> searchCustomerTraceRecordByParams(@Param("map") Map<String, String> map, Pageable pageable) {
        return customerTraceRecordMapper.searchCustomerTraceRecordByParams(map,pageable);
    }

    @Override
    public List<CustomerTraceRecord> searchCustomerTraceRecordByParams(@Param("map") Map<String, String> map) {
        return customerTraceRecordMapper.searchCustomerTraceRecordByParams(map);
    }
}
