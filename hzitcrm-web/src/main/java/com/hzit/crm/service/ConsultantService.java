package com.hzit.crm.service;

import com.hzit.crm.common.BaseReponse;

import com.hzit.crm.core.mapper.entity.UserInfo;

import java.util.List;

/**
 *
 * Created by yangxiaowei-pc on 2016/8/21.
 */
public interface ConsultantService {

    /**
     * [获取咨询师列表全量]
     *
     */
    List<UserInfo> getAllConsultantList();

    /**
     * [客户注册]
     *  添加客户姓名,并划拨咨询师
     * @param name              客户姓名
     * @param consultantId      咨询师id
     */
    BaseReponse registerCustomer(String name,Integer consultantId);


}
