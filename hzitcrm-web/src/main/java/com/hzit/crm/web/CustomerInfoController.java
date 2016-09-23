package com.hzit.crm.web;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.PageRequest;
import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.service.CustomerInfoService;
import com.hzit.crm.vo.DataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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
        /*PageRequest pageRequest = new PageRequest(0,100);
        Map<String,String> paramMap = null;
        Page<CustomerInfo> page = customerInfoService.customerInfoList(paramMap,pageRequest,);
        List<CustomerInfo> customerInfoList = null;
        if(page != null){
            customerInfoList = page.getContent();
        }
        map.addAttribute("customerInfoList",customerInfoList);*/
        return "/customer/customerList";
    }

    /**
     * 异步方式获取客户信息
     * @return
     */
    @RequestMapping("/customerInfo/ajaxList")
    @ResponseBody
    protected DataGrid<CustomerInfo> ajaxList( String page, String rows,String sort, String order){
        if(rows == null || "".equals(rows)){
            rows="20";
        }
        if(page == null || "".equals(page)){
            page="0";
        }
        if(order == null){
            order = "asc";
        }
        PageRequest pageRequest = new PageRequest(Integer.parseInt(page)-1,Integer.parseInt(rows),sort(sort,order));
        Map<String,String> paramMap = null;
        //Page<CustomerInfo> page2 = customerInfoService.customerInfoList(paramMap,pageRequest,sort,order);

        return  customerInfoService.customerInfoList(paramMap,pageRequest,sort,order);
    }

    /**
     * 服务器排序
     * @param sort
     * @return
     */
    private String sort(String sort,String order){
        String[] strArr = {"real_name","sex","age","native_place","tel","wechat_no","qq","education_bg",
                "graduate_time","graduate_from","major_in","work_age","work_experience","job","educate_experience",
        "recruit_channel","customer_state","customer_level","user_id","target_skill","introducer","memo","last_time","create_time"};
        String sortName = "create_time ";
        if(sort != null){
            for(String str : strArr){
                if(str.replace("_","").equals(sort.toLowerCase())){
                    sortName =  str;
                }
            }
        }
        return sortName+" "+order;
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
     * 录入客户信息(咨询师修改完善客户信息)
      * @return
     */
    @RequestMapping("/editCustomerInfo")
    protected  String editCustomerInfo(CustomerInfo customerInfo ){
        return "index";
    }



}
