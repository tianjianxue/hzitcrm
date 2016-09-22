package com.hzit.crm.service;

import com.hzit.crm.core.entity.UserInfo;

import java.util.List;

/**
 * Created by 冼耀基 on 2016/9/20.
 */
public interface UserInfoService {
    /**
     * 查找所有的咨询师信息
     * @return
     */
    public List<UserInfo> findAll();

}
