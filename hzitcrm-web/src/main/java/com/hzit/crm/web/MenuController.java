package com.hzit.crm.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 吴文杰 on 2016/9/10.
 */
@Controller
public class MenuController
{
    @RequestMapping("/menu")
    public String login(HttpServletRequest request, ModelMap modelMap)
    {
         Object obj=request.getSession().getAttribute("user");
        modelMap.put("list","listdata");
        return "menu";
    }
}
