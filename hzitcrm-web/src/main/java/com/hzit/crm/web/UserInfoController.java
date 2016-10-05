package com.hzit.crm.web;

import com.fc.platform.commons.page.PageRequest;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.service.CustomerInfoService;
import com.hzit.crm.service.UserInfoService;
import com.hzit.crm.vo.DataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 类描述:咨询师访问控制器
 * Created by 冼耀基 on 2016/9/21.
 */
@Controller
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private CustomerInfoService customerInfoService;
    /**
     * 咨询师的客户客户列表
     * @return
     */
    @RequestMapping("/userInfo/customerInfoList")
    protected String customerInfoList(String userId){
        //根据咨询师的id找到属于该咨询师的客户
       return "/userInfo/customerInfoList";
    }

    @RequestMapping("/userInfo/customerInfo")
    @ResponseBody
    protected DataGrid<CustomerInfo> customerInfo(String rows,String page,String sort,String order){
        /**
         * 默认每页20条数据
         */
        if(rows == null || "".equals(rows)){
            rows = "20";
        }
        if(page == null || "".equals(page)){
            page = "0";
        }
        Map<String,String> map = new HashMap<String, String>();
        map.put("userId","1000");
        Pageable pageable = new PageRequest(Integer.parseInt(page)-1,Integer.parseInt(rows),sort(sort,order));
        return userInfoService.customerInfoList(map,pageable);
    }

    /**
     * 我的客户排序
     * @param sort
     * @param order
     * @return
     */
    private String sort(String sort,String order){
        String[] strArr = {"real_name","sex","age","native_place","tel","wechat_no","qq","education_bg",
                "graduate_time","graduate_from","major_in","work_age","work_experience","job","educate_experience",
                "recruit_channel","customer_state","customer_level","user_id","target_skill","introducer","memo","last_time","create_time"};
        String sortName = "user_id ";
        if(order == null){
            order = "ASC";
        }
        if(sort != null){
            for(String str : strArr){
                if(str.replace("_","").equals(sort.toLowerCase())){
                    sortName =  str;
                }
            }
        }
        return sortName+" "+order;
    }


}
