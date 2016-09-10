package com.hzit.crm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by yangxiaowei-pc on 2016/9/10.
 */
@Controller
public class LoginController
{

    @RequestMapping("/login")
    public String login(@RequestParam String userId,@RequestParam String pwd)
    {
        //匹配用户名密码 md5加密密码
        //解析权限
        return null;
    }
}
