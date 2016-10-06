package com.hzit.crm.service;

import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.core.entity.UserInfo;
import com.hzit.crm.vo.DataGrid;

import java.util.List;
import java.util.Map;

/**
 * Created by 冼耀基 on 2016/9/20.
 */
public interface UserInfoService {
    /**
     * 查找所有的咨询师信息
     * @return
     */
    public List<UserInfo> findAll();

    /**
     * 获取当前咨询师的客户信息
     * @return
     */
    public DataGrid<CustomerInfo> customerInfoList(Map<String,String> map,Pageable pageable);

}
