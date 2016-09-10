package com.hzit.crm.service.impl;

import com.hzit.crm.common.BaseReponse;
import com.hzit.crm.common.CustomerStateEnum;
import com.hzit.crm.common.ResponseEnum;
import com.hzit.crm.common.RoleEnum;

import com.hzit.crm.core.mapper.entity.CustomerInfo;
import com.hzit.crm.core.mapper.entity.UserInfo;
import com.hzit.crm.core.mapper.mapper.CustomerInfoMapper;
import com.hzit.crm.core.mapper.mapper.UserInfoMapper;
import com.hzit.crm.service.ConsultantService;
import com.hzit.crm.util.DateUtils;
import javafx.scene.input.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiaowei-pc on 2016/8/21.
 */
@Service
public class ConsultantServiceImpl implements ConsultantService{

    @Autowired
    private CustomerInfoMapper customerDao;

    @Autowired
    private UserInfoMapper userDao;

    /**
     * [获取咨询师列表全量]
     */
    @Override
    public List<UserInfo> getAllConsultantList() {

        Map<String,String> paramMap = new HashMap<String, String>();
        paramMap.put("roleId", RoleEnum.CONSULTANT.getRoleType().toString());
        List<UserInfo> resultList = userDao.searchUserInfoByParams(paramMap);
        System.out.println("ConsultantServiceImpl.getAllConsultantList return:" +resultList);
        return resultList;

    }



    /**
     *
     * [客户注册]
     *  添加客户姓名,并划拨咨询师
     *  TODO  客户状态初始化
     * @param name              客户姓名
     * @param consultantId      咨询师id
     *
     */
    @Override
    public BaseReponse registerCustomer(String name, Integer consultantId) {
        BaseReponse baseReponse = new BaseReponse();
        try {
            CustomerInfo cInfo = new CustomerInfo();
            cInfo.setRealName(name);
            cInfo.setCustomerState(CustomerStateEnum.REGISTERED.getStateCode());
            cInfo.setUserId(consultantId);
            String nowTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
            cInfo.setCreateTime(nowTime);  //客户登记时间    格式 yy-MM-dd hh:mi:ss
            cInfo.setLastTime(nowTime);    //最后处理时间
            customerDao.insertCustomerInfo(cInfo);
            System.out.println("ConsultantServiceImpl.registerCustomer CustomerInfo is:" + cInfo);
        }  catch (Exception ex) {
            baseReponse.setResponseState(ResponseEnum.FAILURE);
            return baseReponse;
        }
        baseReponse.setResponseState(ResponseEnum.SUCCESS);
        return baseReponse;
    }

}
