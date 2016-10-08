package com.hzit.service;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.PageRequest;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.ApplicationWeb;
import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.service.CustomerInfoService;
import com.hzit.crm.service.CustomerService;
import com.hzit.crm.service.CustomerTraceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 冼耀基 on 2016/10/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationWeb.class)
public class CustomerTraceServiceTest {
    @Autowired
    private CustomerService customerService;

    @Autowired
   private CustomerInfoService customerInfoService;
    @Test
    public void test(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("userId","1001");
        Pageable pageable = new PageRequest(0,5);
        Page<CustomerInfo> page = customerInfoService.pageCustomerTrace(map,pageable);
        List<CustomerInfo> list = page.getContent();
        System.out.println("总页数:"+page.getTotalPages());
        if(list != null || list.size() >0){
            System.out.println("数据:"+list.size());
            for(CustomerInfo customerInfo : list){
                System.out.println(customerInfo.toString());
            }
        }
    }

}
