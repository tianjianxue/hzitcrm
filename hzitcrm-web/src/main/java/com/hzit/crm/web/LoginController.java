package com.hzit.crm.web;

import com.hzit.crm.core.entity.UserInfo;
import com.hzit.crm.service.ModuleService;
import com.hzit.crm.service.UserInfoService;
import com.hzit.crm.util.SHAUtil;
import com.hzit.crm.vo.WxUserinfoVo;
import com.hzit.crm.weixin.WxCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiaowei-pc on 2016/9/10.
 */
@Controller
public class LoginController extends  BaseController {
   /* @Autowired
    WxCommand wxCommand;*/

    @Autowired
    ModuleService moduleService;

    @Autowired
    private UserInfoService userInfoService;
    /*
    //这个方法用来测试登录的。实际上线的时候必须切掉这个控制器路径
    @RequestMapping("/testLogin")
    public String login(@RequestParam("weno") String weno, HttpSession session)
    {
        WxUserinfoVo wx=new WxUserinfoVo(weno,"jack");  //测试代码
        session.setAttribute("userinfo",wx);
        return "redirect:/index";
    }*/
    /*@RequestMapping("/logout")
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/index.jsp";
    }*/


   /* @RequestMapping("/weixinLogin")
    public String weixinLogin(@RequestParam(name="auth_code",defaultValue = "") String auth_code, @RequestParam(name="expires_in",defaultValue = "") String expires_in, HttpSession session, ModelMap modelMap)
    {
        WxUserinfoVo wx=wxCommand.getWxUserJson(auth_code);
        if(wx==null){
                return "redirect:/index.jsp";
        }else{
            session.setAttribute("userinfo",wx);
        }
        return "index";

    }
*/

    /**
     * 传统方式登录
     * 跳转到登录页面
     */
    @RequestMapping("/loginui")
    protected  String loginui(){
        return "login";
    }

    @RequestMapping("/login")
    protected String login(UserInfo userInfo,HttpServletRequest request) throws Exception {
        userInfo.setPassword(SHAUtil.shaEncode(userInfo.getPassword()));
        String url;
        Map<String,String> map = new HashMap<String, String>();
        map.put("name",userInfo.getName());
        map.put("password",userInfo.getPassword());
        List<UserInfo> userInfoList = userInfoService.searchCustomerInfoByParams(map);
        UserInfo userInfo1 = null;
        if(userInfoList != null && userInfoList.size() >0){//登录成功!
            userInfo1 = userInfoList.get(0);
            userInfo1.setWechatNo("");
            userInfo1.setPassword("");
            HttpSession httpSession = request.getSession();
            httpSession.setMaxInactiveInterval(-1);//永久有效(session不超时)
            httpSession.setAttribute("userinfo",userInfo1);
            url = "redirect:/index";
        }else{//登录失败!
            url = "redirect:/loginui";
        }
        return url;
    }

    /**
     * 注销系统
     * @return
     */
    @RequestMapping("/logout")
    protected String logout(HttpServletRequest request){
        HttpSession httpSession = request.getSession(false);
        Object obj = httpSession.getAttribute("userinfo");
        if(obj != null){
            httpSession.invalidate();
        }
        //注销session
        return this.loginui();
    }

}
