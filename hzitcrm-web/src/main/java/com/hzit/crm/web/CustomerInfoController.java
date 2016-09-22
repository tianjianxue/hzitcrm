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
public class CustomerInfoController extends  BaseController{
    @Autowired
    private CustomerInfoService customerInfoService;

    /**
     * 获取客户信息
     * @return
     */
    @RequestMapping("/customerInfoList")
    @ResponseBody
    protected List<CustomerInfo> customerInfoList(){
        return customerInfoService.findByNameAndState();
    }


    /**
     * 使用者(咨询师)
     * 跳转到录入客户信息详情表单页
     */
    @RequestMapping("/typedCustomerInfo")
    protected  String typedCustomerInfo(){
        return "/customer/typedCustomerInfo";
    }

    /**
     * 录入客户信息
      * @return
     */
    @RequestMapping("/editCustomerInfo")
    protected  String editCustomerInfo(CustomerInfo customerInfo ){
        return "index";
    }
}
