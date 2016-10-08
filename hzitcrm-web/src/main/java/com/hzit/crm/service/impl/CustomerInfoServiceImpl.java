package com.hzit.crm.service.impl;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.core.entity.UserInfo;
import com.hzit.crm.core.mapper.CustomerInfoMapper;
import com.hzit.crm.core.mapper.UserInfoMapper;
import com.hzit.crm.service.CustomerInfoService;
import com.hzit.crm.vo.CustomerInfoVo;
import com.hzit.crm.vo.DataGrid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by 冼耀基 on 2016/9/20.
 */
@Service
@Transactional//开启事务
public class CustomerInfoServiceImpl implements CustomerInfoService {
    @Autowired
    private CustomerInfoMapper customerInfoMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;
    /**
     * 获取当前日期的来访客户
     * @return
     */
    @Override
    public List<CustomerInfo> findByNameAndState() {

       /* SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = simpleDateFormat.format(new Date());
        Map<String,String> map = new HashMap<String,String>();
        map.put("createTime",currentDate);*/
        return customerInfoMapper.findByNameAndState();
    }

    /**
     * 保存客户的真实名称和咨询师id
     */
    @Override
    public void insertByRealNameAndUserId(CustomerInfo customerInfo) {
        customerInfoMapper.insertCustomerInfo(customerInfo);
    }


    /**
     * 录入客户信息
     * @param customerInfo
     */
    @Override
    public void updateCustomerInfo(CustomerInfo customerInfo) {
        customerInfoMapper.updateCustomerInfo(customerInfo);
    }

    /**
     * 获取客户列表
     * @param map
     * @param pageable
     * @return
     */
    @Override
    public DataGrid<CustomerInfoVo> customerInfoList(Map<String, String> map, Pageable pageable,String sort,String order) {

        //BeanUtils.copyProperties();
        Page<CustomerInfo> page = customerInfoMapper.searchCustomerInfoByParams(map,pageable);
        DataGrid<CustomerInfoVo> dataGrid = new DataGrid<CustomerInfoVo>();
        dataGrid.setTotal(customerInfoMapper.getTotal());
        List<CustomerInfoVo> customerInfoVos = new ArrayList<CustomerInfoVo>();
        CustomerInfoVo customerInfoVo = null;
        if(page != null){
            for(CustomerInfo customerInfo : page.getContent()){
                customerInfoVo = new CustomerInfoVo();
                BeanUtils.copyProperties(customerInfo,customerInfoVo);
                for(UserInfo userInfo : userInfoMapper.findAll()){
                    if(userInfo.getUserId().equals(customerInfoVo.getUserId())){
                        customerInfoVo.setName(userInfo.getName());
                        customerInfoVo.setUserName(userInfo.getRealName());
                    }
                }
                customerInfoVos.add(customerInfoVo);
            }

            dataGrid.setRows(customerInfoVos);
        }
        return dataGrid;
    }

    /**
     * 根据客户id查找客户信息
     * @param customerId
     * @return
     */
    @Override
    public CustomerInfo findCustomerInfoById(String customerId) {
        Map<String ,String> map =  new Hashtable<String, String>();
        map.put("customerId",customerId);
        List<CustomerInfo> list = customerInfoMapper.searchCustomerInfoByParams(map);
        CustomerInfo customerInfo = null;
        if(list != null && list.size() >0){
            customerInfo = list.get(0);
        }
        return customerInfo;
    }

    /**
     * 客户跟进时获取客户相应信息
     * @param userId
     * @return
     */
    @Override
    public List<CustomerInfo> showCustomerTrace(String userId) {
        Map<String,String> map = new HashMap<String, String>();
        map.put("userId",userId);
        return customerInfoMapper.showCustomerTrace(map);
    }

    @Override
    public Page<CustomerInfo> pageCustomerTrace(Map<String,String> map,Pageable pageable) {
        return customerInfoMapper.searchCustomerInfoByParams(map,pageable);
    }

}
