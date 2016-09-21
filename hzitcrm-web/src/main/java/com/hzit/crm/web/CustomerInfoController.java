package com.hzit.crm.web;

import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.service.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 冼耀基 on 2016/9/20.
 */

@Controller
public class CustomerInfoController {
    @Autowired
    private CustomerInfoService customerInfoService;
    @RequestMapping("/customerInfoList")
    @ResponseBody
    protected List<CustomerInfo> customerInfoList(){
        return customerInfoService.findByNameAndState();
    }
}
