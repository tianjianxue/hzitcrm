package com.hzit.crm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/8/20.
 */
@Controller
public class IndexController {


    @RequestMapping("/add")
    public void one(){
        System.out.println("处理用户新增的请求");
    }

    @RequestMapping("/zixunshi")
    public String zixunshi(){
        System.out.println("处理用户新增的请求");
        return "zixunshi";
    }


}
