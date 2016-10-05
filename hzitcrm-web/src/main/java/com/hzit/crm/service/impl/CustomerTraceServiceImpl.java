package com.hzit.crm.service.impl;

import com.hzit.crm.core.entity.CustomerTraceRecord;
import com.hzit.crm.core.entity.UserInfo;
import com.hzit.crm.core.mapper.CustomerTraceRecordMapper;
import com.hzit.crm.core.mapper.UserInfoMapper;
import com.hzit.crm.service.CustomerTraceService;
import com.hzit.crm.vo.CustomerTraceRecordVo;
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
}
