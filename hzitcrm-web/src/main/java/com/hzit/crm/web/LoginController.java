package com.hzit.crm.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.hzit.crm.core.entity.Module;
import com.hzit.crm.service.ModuleService;
import com.hzit.crm.vo.WxUserinfoVo;
import com.hzit.crm.weixin.WxCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by yangxiaowei-pc on 2016/9/10.
 */
@Controller
public class LoginController
{
    @Autowired
    WxCommand wxCommand;

    @Autowired
    ModuleService moduleService;

    //这个方法用来测试登录的。实际上线的时候必须切掉这个控制器路径
    @RequestMapping("/testLogin")
    public String login(HttpSession session)
    {
        WxUserinfoVo wx=new WxUserinfoVo("1","jack");  //测试代码
        session.setAttribute("userinfo",wx);
        return "index";
    }

    @RequestMapping("/weixinLogin")
    public String weixinLogin(@RequestParam(name="auth_code",defaultValue = "") String auth_code, @RequestParam(name="expires_in",defaultValue = "") String expires_in, HttpSession session, ModelMap modelMap)
    {
        WxUserinfoVo wx=wxCommand.getWxUserJson(auth_code);
        if(wx==null){
                return "redirect:/index.jsp";
        }else{
            session.setAttribute("userinfo",wx);
            List<Module> mlist= moduleService.searchModuleByWeCharNo(wx.getUserid());
            modelMap.put("mlist",mlist);
        }
        return "index";

    }
}
