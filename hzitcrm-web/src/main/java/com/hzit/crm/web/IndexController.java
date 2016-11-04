package com.hzit.crm.web;

import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.core.entity.Module;
import com.hzit.crm.core.entity.RoleModule;
import com.hzit.crm.core.entity.UserInfo;
import com.hzit.crm.service.CustomerInfoService;
import com.hzit.crm.service.RoleService;
import com.hzit.crm.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 系统首页控制器
 * Created by Administrator on 2016/8/20.
 */
@Controller
public class IndexController extends  BaseController{

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private RoleService roleService;

    /**
     * 登陆后默认显示页
     * @return
     */
    @RequestMapping("/foreground")
    protected String foreground(){
        return "/foreground/index";
    }

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
    protected  String index(HttpServletRequest request, Model map){
        HttpSession httpSession = request.getSession(false);
        UserInfo userInfo = (UserInfo)httpSession.getAttribute("userinfo");
        List<RoleModule> roleModuleList = roleService.findModuleByUserId(userInfo.getUserId()+"");
        List<Module> moduleList = null;
        if(roleModuleList != null && roleModuleList.size() >0){
            RoleModule roleModule = roleModuleList.get(0);
            moduleList = roleModule.getModules();
            map.addAttribute("moduleList",moduleList);
        }
        return "/index";
    }

    /**
     * json
     * @return
     */
    @RequestMapping("/userInfoList")
    @ResponseBody
    protected List<UserInfo> userInfoList(HttpServletRequest request) throws IOException {
        HttpSession httpSession = request.getSession(false);
        UserInfo userInfo = (UserInfo)httpSession.getAttribute("userinfo");
        //从配置文件中获取客户状态
        Properties properties = new Properties();
        File file = ResourceUtils.getFile("classpath:userInfo.properties");
        InputStream inputStream = new FileInputStream(file);
        properties.load(inputStream);
        Map<String,String> map = new HashMap<String, String>();
        map.put("roleId",properties.get("roleId")+"");//获取咨询师角色编号
        map.put("roleId2",properties.get("roleId2")+"");//咨询主管
        map.put("companyId",userInfo.getCompanyId()+"");
        List<UserInfo> userInfoList = userInfoService.searchUserInfoByRoleAndCompany(map);
        return userInfoList;
    }

    /**
     * 首页中初步保存客户信息
     * @param customerInfo
     * @return
     */
    @RequestMapping("/userInfo/add")
    @ResponseBody
    protected String add(CustomerInfo customerInfo, HttpServletRequest request){
        //从session中获取用户id
        HttpSession httpSession =request.getSession(false);
        UserInfo userInfo = (UserInfo)httpSession.getAttribute("userinfo");
        String msg = null;
        if(customerInfo.getRealName() != null && !"".equals(customerInfo.getRealName())){
           try{
               customerInfo.setCustomerState(1);//默认为等待状态
               SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               customerInfo.setCreateTime(simpleDateFormat.format(new Date()));
               customerInfoService.insertByRealNameAndUserId(customerInfo,userInfo.getUserId()+"");
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


   /* @RequestMapping("/foreground")
    protected String foreground(){
        return "foreground/index";
    }*/
}
