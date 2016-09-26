package com.hzit.crm.service.impl;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.core.entity.UserInfo;
import com.hzit.crm.core.mapper.CustomerInfoMapper;
import com.hzit.crm.core.mapper.UserInfoMapper;
import com.hzit.crm.service.UserInfoService;
import com.hzit.crm.vo.DataGrid;
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
public class UserInfoServiceImpl implements UserInfoService{
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private CustomerInfoMapper customerInfoMapper;

    @Override
    public List<UserInfo> findAll() {
        return userInfoMapper.findAll();
    }

    /**
     * 获取当前咨询师的客户信息
     * @return
     */
    @Override
    public DataGrid<CustomerInfo> customerInfoList(Map<String,String> map,Pageable pageable) {
        DataGrid<CustomerInfo> dataGrid = new DataGrid<CustomerInfo>();
        Page<CustomerInfo> customerInfos = customerInfoMapper.searchCustomerInfoByParams(map,pageable);
        if(customerInfos != null){
            dataGrid.setTotal(customerInfos.getTotalElements());
            dataGrid.setRows(customerInfos.getContent());
        }
        return dataGrid;
    }
}
