package com.hzit.crm.service.impl;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.core.mapper.CustomerInfoMapper;
import com.hzit.crm.service.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by 冼耀基 on 2016/9/20.
 */
@Service
@Transactional
public class CustomerInfoServiceImpl implements CustomerInfoService {
    @Autowired
    private CustomerInfoMapper customerInfoMapper;

    /**
     * 获取当前日期的来访客户
     * @return
     */
    @Override
    public List<CustomerInfo> findByNameAndState() {
       /* SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = simpleDateFormat.format(new Date());
        Map<String,String> map = new HashMap<String,String>();
        map.put("createTime",currentDate);*/
        return customerInfoMapper.findByNameAndState();
    }

    /**
     * 保存客户的真实名称和咨询师id
     */
    @Override
    public void insertByRealNameAndUserId(CustomerInfo customerInfo) {
        customerInfoMapper.insertCustomerInfo(customerInfo);
    }

    /**
     * 获取客户表的总记录数
     * @return
     */
    @Override
    public int getTotal() {
        return customerInfoMapper.getTotal();
    }

    /**
     * 录入客户信息
     * @param customerInfo
     */
    @Override
    public void updateCustomerInfo(CustomerInfo customerInfo) {

    }

    /**
     * 获取客户列表
     * @param map
     * @param pageable
     * @return
     */
    @Override
    public Page<CustomerInfo> customerInfoList(Map<String, String> map, Pageable pageable) {
        return customerInfoMapper.searchCustomerInfoByParams(map,pageable);
    }

}
