package com.hzit.crm.service.impl;

import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.core.mapper.CustomerInfoMapper;
import com.hzit.crm.service.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 冼耀基 on 2016/9/20.
 */
@Service
@Transactional
public class CustomerInfoServiceImpl implements CustomerInfoService {
    @Autowired
    private CustomerInfoMapper customerInfoMapper;
    @Override
    public List<CustomerInfo> findByNameAndState() {
        return customerInfoMapper.findByNameAndState();
    }
}
