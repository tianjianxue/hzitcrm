package com.hzit.mapper.test;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.PageRequest;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.ApplicationWeb;
import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.core.mapper.CustomerInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 冼耀基 on 2016/10/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationWeb.class)
public class CustomerInfoMapperTest {
    @Autowired
    private CustomerInfoMapper customerInfoMapper;
    @Test
    public void test(){
        Pageable pageable = new PageRequest(0,10);
        Map<String,String> map = new HashMap<String,String>();
        map.put("companyId","2");
        Page<CustomerInfo>  page = customerInfoMapper.searchCustomerInfoByParamsLike(map,pageable);
        System.out.println("总数据:"+page.getTotalElements());
        if(page != null){
            for(CustomerInfo customerInfo : page.getContent()){
                System.out.println(customerInfo.toString());
            }
            System.out.println("-----------------------------");
            System.out.println("总记录数:"+page.getTotalElements());
        }
    }
}
