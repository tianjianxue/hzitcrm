package com.hzit.crm.web;

import com.hzit.crm.core.entity.UserInfo;
import com.hzit.crm.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 系统首页控制器
 * Created by Administrator on 2016/8/20.
 */
@Controller
public class IndexController {
    @Autowired
    private UserInfoService userInfoService;
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
     * 到数据库中查找相应咨询师的信息
     * @return
     */
    @RequestMapping("/index")
    protected  String index(){
        return "index";
    }

    @RequestMapping("/userInfoList")
    @ResponseBody
    protected List<UserInfo> userInfoList(){
        return userInfoService.findAll();
    }


    @RequestMapping("/userInfo/add")
    @ResponseBody
    protected String add(UserInfo userInfo){
        if(userInfo != null){
            System.out.println(userInfo.getName());
        }
        return "添加成功!";
    }

}
