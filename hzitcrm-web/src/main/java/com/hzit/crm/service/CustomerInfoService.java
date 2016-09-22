package com.hzit.crm.service;

import com.hzit.crm.core.entity.CustomerInfo;

import java.util.List;

/**
 * Created by 冼耀基 on 2016/9/20.
 */
public interface CustomerInfoService {
    /**
     * 获取客户信息的名称和状态
     * @return
     */
    public List<CustomerInfo> findByNameAndState();


    /**
     * 保存客户的真实名称和咨询师id
     */
    public void insertByRealNameAndUserId(CustomerInfo customerInfo);

    public int getTotal();


    public void updateCustomerInfo(CustomerInfo customerInfo);
}
