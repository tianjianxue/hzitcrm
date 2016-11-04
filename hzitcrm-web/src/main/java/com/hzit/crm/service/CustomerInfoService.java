package com.hzit.crm.service;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.vo.CustomerInfoVo;
import com.hzit.crm.vo.DataGrid;
import org.apache.ibatis.annotations.Param;

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
    public List<CustomerInfoVo> findByNameAndState();

    void insertCustomerInfo(CustomerInfo customerInfo);
    /**
     * 保存客户的真实名称和咨询师id
     */
    public void insertByRealNameAndUserId(CustomerInfo customerInfo,String userId);



    public void updateCustomerInfo(CustomerInfo customerInfo);

    public DataGrid<CustomerInfoVo> customerInfoList(Map<String,String> map, Pageable pageable);

    CustomerInfo findCustomerInfoById(String customerId);

    public List<CustomerInfo> showCustomerTrace(String userId);

    public Page<CustomerInfo> pageCustomerTrace(Map<String,String> map,Pageable pageable);
    public List<CustomerInfo> searchCustomerInfoByParams(Map<String,String> map);
    /**
     * 模糊查询
     * @param map
     * @param pageable
     * @return
     */
    DataGrid<CustomerInfoVo> searchCustomerInfoByParamsLike(@Param("map")Map<String,String> map, Pageable pageable);
}
