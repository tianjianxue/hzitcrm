package com.hzit.crm.service;

import com.fc.platform.commons.page.PageRequest;
import com.hzit.crm.core.mapper.entity.CustomerInfo;
import com.hzit.crm.core.mapper.entity.CustomerTraceRecord;

import java.util.List;
import java.util.Map;


/**
* 客户服务接口
* Created by yangxiaowei-pc on 2016/8/24.
*/
public interface CustomerService {

    /**
     * 获取所有注册客户信息
     * @return
     */
    List<CustomerInfo> getAllRegisterCustomer();

    /**
     * 获取某个咨询师旗下的某状态的客户信息
     * TODO 有待 Order by 和 状态过滤
     * @retur
     */
    Map<String,Object> getCustomerInfoListByConsultantId(CustomerInfo param,PageRequest pageRequest);


    /**
     * 获取某个客户的访谈记录
     * @return
     */
    List<CustomerTraceRecord> getCustomerTraceRecordById(CustomerTraceRecord param,PageRequest pageRequest);

    /**
     * 添加客户跟进记录
     * @param recored
     */
    boolean addCustomerTraceItem(CustomerTraceRecord recored);

}
