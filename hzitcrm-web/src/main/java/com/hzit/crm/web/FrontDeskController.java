package com.hzit.crm.web;

import com.alibaba.druid.util.StringUtils;
import com.hzit.crm.common.BaseReponse;
import com.hzit.crm.common.ResponseEnum;

import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.core.entity.UserInfo;
import com.hzit.crm.service.ConsultantService;
import com.hzit.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 前台人员控制器
 * Created by yangxiaowei-pc on 2016/8/21.
 */
@Controller
public class FrontDeskController {

    @Autowired
    private ConsultantService consultantService;

    @Autowired
    CustomerService customerService;

    /**
     * 进入主界面，获取咨询师列表
     * @param modelMap
     */
    @RequestMapping("/front-desk")
    public String getConsultantList(ModelMap modelMap)
    {
        List<UserInfo> consultantList = consultantService.getAllConsultantList();
        List<CustomerInfo> customerInfoList = customerService.getAllRegisterCustomer();
        modelMap.put("consultantList",consultantList);
        modelMap.put("customerInfoList",customerInfoList);
        return "front-desk";

    }

    /**
     * 客户注册
     * (访客到来，前台人员录入姓名)
     */
    @ResponseBody
    @RequestMapping("/registerCustomer")
    public Object registerCustomer(@RequestParam(name ="customerName",required = false) String  customerName,
                                   @RequestParam(name ="consultantId",required = false) Integer consultantId)
    {

        BaseReponse response = new BaseReponse();
        if(StringUtils.isEmpty(customerName)){
            response.setResponseState(ResponseEnum.CUSTOMER_NAME_IS_BLANK);
            System.err.println("并没有录入客户姓名~~~ response="+response);
        } else
        if(consultantId==null || consultantId==0){
            response.setResponseState(ResponseEnum.CUSTOMER_NAME_IS_BLANK);
            System.err.println("并没有选择咨询师~~~ response="+response);
        } else
        {
            response = consultantService.registerCustomer(customerName, consultantId);
            System.out.println("登记客户:customerName="+customerName+",consultantId="+consultantId);
        }
        return response;
    }


    /**
     * 定时刷新访客[客户]列表
     * TODO 等待时间待加字段 register_time  每次计算时间差
     */
    @ResponseBody
    @RequestMapping("/getCustomerInfoList")
    public Object getRegisterCustomerList() {
        List<CustomerInfo> customerInfoList = customerService.getAllRegisterCustomer();
        System.err.println("状态为已注册的用户列表为:" + customerInfoList);
        return customerInfoList;
    }


}
