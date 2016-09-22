package com.hzit.crm.service;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.CustomerInfo;

import java.util.List;
import java.util.Map;

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

    public Page<CustomerInfo> customerInfoList(Map<String,String> map, Pageable pageable);
}
