package com.hzit.crm.web;

import com.fc.platform.commons.page.PageRequest;
import com.hzit.crm.core.entity.UserInfo;
import com.hzit.crm.service.UserInfoService;
import com.hzit.crm.vo.DataGrid;
import com.hzit.crm.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 市场经理使用
 * Created by 冼耀基 on 2016/10/29.
 */
@Controller
public class MarketContrller {
    @Autowired
    private UserInfoService userInfoService;
    /**
     * 市场经理只能查看前台的客户等待状况
     * @return
     */
    @RequestMapping("/market/qiantai")
    protected String qiantai(){
        return "/market/qiantai";
    }

    /**
     * 客户列表
     * @return
     */
    @RequestMapping("/market/customerInfoList")
    protected String custonerInfoList(){
        return "/market/customerInfoList";
    }

    /**
     * 获取分公司所有已报名客户
     * @return
     */
    @RequestMapping("/userInfo/allCustomerState")
    protected String allCustomerState(){
        return "/market/allCustomerState";
    }

    /**
     * 获取员工信息(只读)
     * @return
     */
    @RequestMapping("/market/allUserInfo")
    protected String allUserInfo(){
        return "/market/allUserInfo";
    }

    /**
     * 咨询师:2,咨询部门主管:3，创量人员:14,创量主管:15
     * 获取本公司创量，会销和咨询的人员信息
     */
    @RequestMapping("/market/thirdPartUserInfo")
    protected String thirdPartUserInfo(){
        return "/market/thirdPartUserInfo";
    }

    @RequestMapping("/markert/thirdPartPartUserInfoData")
    @ResponseBody
    protected DataGrid<UserInfoVo> thirdPartPartUserInfoData(String page, String rows, HttpServletRequest request){
        if(rows == null || "".equals(rows)){
            rows="20";
        }
        if(page == null || "".equals(page)){
            page="1";
        }
        HttpSession httpSession = request.getSession(false);
        UserInfo userInfo = (UserInfo)httpSession.getAttribute("userinfo");
        Map<String,String>  companyIdMap = new HashMap<String, String>();
        companyIdMap.put("companyId",userInfo.getCompanyId()+"");
        //排序
        PageRequest pageRequest = new PageRequest(Integer.parseInt(page)-1,Integer.parseInt(rows));
        return userInfoService.thirdPartUserInfo(companyIdMap,pageRequest);
    }
}
