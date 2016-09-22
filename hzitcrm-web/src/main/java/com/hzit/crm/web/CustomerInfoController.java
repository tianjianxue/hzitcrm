package com.hzit.crm.web;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.PageRequest;
import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.service.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by 冼耀基 on 2016/9/20.
 */

@Controller
public class CustomerInfoController extends  BaseController{
    @Autowired
    private CustomerInfoService customerInfoService;

    /**
     * 获取客户信息(主页来访列表用)
     * @return
     */
    @RequestMapping("/customerInfoList")
    @ResponseBody
    protected List<CustomerInfo> customerInfoList(){
        return customerInfoService.findByNameAndState();
    }

    /**
     * 跳转到客户信息列表
     * @param map
     * @return
     */
    @RequestMapping("/customerInfo/list")
    protected  String list(Model map){
        PageRequest pageRequest = new PageRequest(0,100);
        Map<String,String> paramMap = null;
        Page<CustomerInfo> page = customerInfoService.customerInfoList(paramMap,pageRequest);
        List<CustomerInfo> customerInfoList = null;
        if(page != null){
            customerInfoList = page.getContent();
        }
        map.addAttribute("customerInfoList",customerInfoList);
        return "/customer/customerList";
    }

    /**
     * 异步方式获取客户信息
     * @return
     */
    @RequestMapping("/customerInfo/ajaxList")
    @ResponseBody
    protected List<CustomerInfo> ajaxList(){
        PageRequest pageRequest = new PageRequest(0,100);
        Map<String,String> paramMap = null;
        Page<CustomerInfo> page = customerInfoService.customerInfoList(paramMap,pageRequest);
        List<CustomerInfo> customerInfoList = null;
        if(page != null){
            customerInfoList = page.getContent();
        }
        return  customerInfoList;
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
