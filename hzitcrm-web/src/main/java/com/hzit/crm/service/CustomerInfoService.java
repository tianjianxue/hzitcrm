package com.hzit.crm.service;

import com.hzit.crm.core.entity.CustomerInfo;

import java.util.List;

/**
 * Created by 冼耀基 on 2016/9/20.
 */
public interface CustomerInfoService {
    public List<CustomerInfo> findByNameAndState();
}
