package com.hzit.crm.service.impl;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.CustomerState;
import com.hzit.crm.core.mapper.CustomerStateMapper;
import com.hzit.crm.service.CustomerStateService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by 冼耀基 on 2016/10/30.
 */
@Service
@Transactional
public class CustomerStateServiceImpl implements CustomerStateService {
    @Autowired
    private CustomerStateMapper customerStateMapper;
    @Override
    public void insertCustomerState(CustomerState customerState) {
        customerStateMapper.insertCustomerState(customerState);
    }

    @Override
    public void deleteCustomerStateByStateId(Integer stateId) {
        customerStateMapper.deleteCustomerStateByStateId(stateId);
    }

    @Override
    public void updateCustomerState(CustomerState customerState) {
        customerStateMapper.updateCustomerState(customerState);
    }

    @Override
    public Page<CustomerState> searchCustomerStateByParams(@Param("map") Map<String, String> map, Pageable pageable) {
        return customerStateMapper.searchCustomerStateByParams(map,pageable);
    }

    @Override
    public List<CustomerState> searchCustomerStateByParams(@Param("map") Map<String, String> map) {
        return customerStateMapper.searchCustomerStateByParams(map);
    }
}
