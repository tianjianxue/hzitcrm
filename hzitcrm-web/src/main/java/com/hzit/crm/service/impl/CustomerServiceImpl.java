package com.hzit.crm.service.impl;


import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.PageRequest;

import com.hzit.crm.common.CustomerStateEnum;

import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.core.entity.CustomerTraceRecord;
import com.hzit.crm.core.mapper.mapper.CustomerInfoMapper;
import com.hzit.crm.core.mapper.mapper.CustomerTraceRecordMapper;
import com.hzit.crm.service.CustomerService;
import com.hzit.crm.util.BeanMapConvertUtil;
import com.hzit.crm.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* Created by yangxiaowei-pc on 2016/8/24.
*/
@Service
public class CustomerServiceImpl implements CustomerService
{

    private Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerInfoMapper customerDao;

    @Autowired
    private CustomerTraceRecordMapper recordDao;


    /**
     * 获取所有状态为已注册的客户列表
     */
    @Override
    public List<CustomerInfo> getAllRegisterCustomer()
    {
        Map<String,String> paramMap = new HashMap<String, String>();
        //*REGISTERED-已注册
        paramMap.put("customerState", String.valueOf(CustomerStateEnum.REGISTERED.getStateCode()));
        List<CustomerInfo> resultList = null;
        try
        {
            resultList = customerDao.searchCustomerInfoByParams(paramMap);
            //计算等待时间
            for (CustomerInfo cInfo : resultList )
            {
                String createTime = cInfo.getCreateTime();
                if(StringUtils.isNotBlank(createTime))
                {
                    Date regTime = DateUtils.parse(createTime,DateUtils.FORMAT_LONG);
                    long waitTime = new Date().getTime() - regTime.getTime();   //等待的毫秒数
                    long waitHour = waitTime/(1000*60*60);
                    long waitMin = waitTime%(1000*60*60)/60;
                    String waitTimeStr = waitHour + "小时" +waitMin + "分";
                    cInfo.setMemo(waitTimeStr);                                 //暂时将等待时间设置为备注字段
                }
            }
        }
        catch (Exception e)
        {
            resultList = new ArrayList<CustomerInfo>();
            log.info("getAllRegisterCustomer error.");
            log.error("getAllRegisterCustomer error.",e);
        }
        return resultList;
    }

    /**
     * 获取某个咨询师旗下的非成交客户信息
     * TODO [待添加 排序 状态筛选！！]
     *
     * @param param
     * @param pageRequest
     */
    @Override
    public Map<String,Object> getCustomerInfoListByConsultantId(CustomerInfo param, PageRequest pageRequest)
    {
        log.info("getCustomerInfoListByConsultantId param is "+ param);
        Map<String,Object> resultMap = new HashMap<String, Object>();
        try{
            Map<String,String> paramMap = BeanMapConvertUtil.toMap(param);
            Page<CustomerInfo> cPage = customerDao.searchCustomerInfoByParams(paramMap, pageRequest);
            List<CustomerInfo> customerInfoList  = cPage.getContent();
            Integer totalPage = cPage.getTotalPages();
            resultMap.put("customerInfoList",customerInfoList);
            resultMap.put("totalPage",totalPage);
        }catch (Exception e)
        {
            log.info("获取客户信息列表失败.");
            log.error("获取客户信息列表失败.",e);
            resultMap.put("totalPage",0);
            resultMap.put("customerInfoList",new ArrayList<CustomerInfo>());
        }
        return resultMap;
    }

    /**
     *
     * 获取某个客户的访谈记录[待分页]
     *
     * @param param
     * @param pageRequest
     * @return
     */
    @Override
    public List<CustomerTraceRecord> getCustomerTraceRecordById(CustomerTraceRecord param, PageRequest pageRequest)
    {
        Map paramMap = BeanMapConvertUtil.toMap(param);
        Page<CustomerTraceRecord> recordsPage = recordDao.searchCustomerTraceRecordByParams(paramMap, pageRequest);
        return recordsPage.getContent();
    }


    /**
     * 添加客户跟进记录
     * @param recored
     * @return
     */
    @Override
    public boolean addCustomerTraceItem(CustomerTraceRecord recored)
    {
        try
        {
            recordDao.insertCustomerTraceRecord(recored);
            CustomerInfo cInfo = new CustomerInfo();
            cInfo.setLastTime(DateUtils.getNow(DateUtils.FORMAT_LONG));
            cInfo.setCustomerId(recored.getCustomerId());
            customerDao.updateCustomerInfo(cInfo);                      //刷新最后跟进时间
            return true;
        }catch (Exception ex)
        {
            log.info("addCustomerTraceItem error.",ex);
            return false;
        }
    }


}
