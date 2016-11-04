package com.hzit.mapper.test;

import com.hzit.crm.ApplicationWeb;
import com.hzit.crm.core.entity.CustomerState;
import com.hzit.crm.core.mapper.CustomerStateMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 冼耀基 on 2016/10/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationWeb.class)
public class CustomerStateMapperTest {
    /*@Autowired
    private CustomerStateMapper customerStateMapper;
    @Test
    public void test(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("stateId","1");
        List<CustomerState> customerStateList = customerStateMapper.searchCustomerStateByParams(map);
        if(customerStateList != null && customerStateList.size() >0){
            for(CustomerState customerState : customerStateList){
                System.out.println(customerState.toString());
            }
        }
    }*/
    @Test
    public void test(){

    }
}
