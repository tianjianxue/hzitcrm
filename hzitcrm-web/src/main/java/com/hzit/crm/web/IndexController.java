package com.hzit.crm.web;

import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.core.entity.UserInfo;
import com.hzit.crm.service.CustomerInfoService;
import com.hzit.crm.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 系统首页控制器
 * Created by Administrator on 2016/8/20.
 */
@Controller
public class IndexController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private CustomerInfoService customerInfoService;


    @RequestMapping("/add")
    public void one(){
        System.out.println("处理用户新增的请求");
    }

    @RequestMapping("/zixunshi")
    public String zixunshi(){
        System.out.println("处理用户新增的请求");
        return "zixunshi";
    }



    /**
     * 跳转到系统首页
     * 异步到数据库中查找相应咨询师的信息
     * @return
     */
    @RequestMapping("/index")
    protected  String index(){
        return "index";
    }

    /**
     * json
     * @return
     */
    @RequestMapping("/userInfoList")
    @ResponseBody
    protected List<UserInfo> userInfoList(){
        return userInfoService.findAll();
    }

    /**
     * 首页中初步保存客户信息
     * @param customerInfo
     * @return
     */
    @RequestMapping("/userInfo/add")
    @ResponseBody
    protected String add(CustomerInfo customerInfo){
        String msg = null;
        if(customerInfo.getRealName() != null && !"".equals(customerInfo.getRealName())){
           try{

               customerInfo.setCustomerState(1);//默认为等待状态
               SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               customerInfo.setCreateTime(simpleDateFormat.format(new Date()));
               customerInfoService.insertByRealNameAndUserId(customerInfo);
               msg = "success";
           }catch (Exception e){
               msg = "failed";
           }
        }else{
            msg = "nullValue";
        }
        return msg;
    }

    @RequestMapping("/layout/welcome")
    protected  String welcome(){
        return "/layout/welcome";
    }

}
