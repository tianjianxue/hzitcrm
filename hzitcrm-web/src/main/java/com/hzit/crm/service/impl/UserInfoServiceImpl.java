package com.hzit.crm.service.impl;

import com.fc.platform.commons.page.PageRequest;
import com.hzit.crm.core.entity.UserInfo;
import com.hzit.crm.core.mapper.UserInfoMapper;
import com.hzit.crm.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 冼耀基 on 2016/9/20.
 */
@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService{
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> findAll() {
        return userInfoMapper.findAll();
    }
}
