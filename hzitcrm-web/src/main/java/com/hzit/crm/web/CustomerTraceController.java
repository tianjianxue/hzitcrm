package com.hzit.crm.web;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.PageRequest;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.core.entity.CustomerState;
import com.hzit.crm.core.entity.CustomerTraceRecord;
import com.hzit.crm.core.entity.UserInfo;
import com.hzit.crm.service.CustomerInfoService;
import com.hzit.crm.service.CustomerStateService;
import com.hzit.crm.service.CustomerTraceService;
import com.hzit.crm.vo.CustomerInfoVo;
import com.hzit.crm.vo.CustomerTraceRecordVo;
import com.hzit.crm.vo.EasyuiMessager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**类描述:客户跟进
 * Created by 冼耀基 on 2016/9/24.
 */
@Controller
public class CustomerTraceController extends  BaseController{
    @Autowired
    private CustomerTraceService customerTraceService;
    @Autowired
    private CustomerInfoService customerInfoService;
    @Autowired
    private CustomerStateService customerStateService;
    @RequestMapping("/customerTrace/list")
    protected String  list(String userId, String page, String pageSize, Model map, HttpServletRequest request){
        String url = "";
        HttpSession httpSession = request.getSession(false);
        UserInfo userInfo = (UserInfo)httpSession.getAttribute("userinfo");
        Map<String,String> paraMap = new HashMap<String, String>();
        if(userId != null){
            paraMap.put("userId",userId);
        }else{
            paraMap.put("userId",userInfo.getUserId()+"");
        }
        if(page == null || "".equals(page)){
            page = "1";
        }
        if(pageSize == null || "".equals(pageSize)){
            pageSize = "5";
        }
        Pageable pageable = new PageRequest(Integer.parseInt(page)-1,Integer.parseInt(pageSize));
        Page<CustomerInfo> customerInfoPage = customerInfoService.pageCustomerTrace(paraMap,pageable);
        List<CustomerInfo> customerInfos = customerInfoService.showCustomerTrace(userInfo.getUserId()+"");
        CustomerInfoVo customerInfoVo = null;
        List<CustomerInfoVo> customerInfoVos = new ArrayList<CustomerInfoVo>();

        Map<String,String> customerStateMap =null;
        for(CustomerInfo customerInfo : customerInfoPage.getContent()){
            customerStateMap = new HashMap<String, String>();
            customerStateMap.put("stateId",customerInfo.getCustomerState()+"");
            customerInfoVo = new CustomerInfoVo();
            BeanUtils.copyProperties(customerInfo,customerInfoVo);
            List<CustomerState> customerStateList = customerStateService.searchCustomerStateByParams(customerStateMap);
            if(customerStateList!=null && customerStateList.size() > 0){
                customerInfoVo.setCustomerStateMsg(customerStateList.get(0).getCustomerState());
            }
            if(customerInfo.getSex() != null){
                if(customerInfo.getSex()==1){ //性别 1：男  2:女
                    customerInfoVo.setSexMsg("男");
                }else if(customerInfo.getSex() == 2){
                    customerInfoVo.setSexMsg("女");
                }
            }else{
                customerInfoVo.setSexMsg("未知");
            }
            customerInfoVos.add(customerInfoVo);
        }
        map.addAttribute("customerTraceList",customerInfoVos);
        map.addAttribute("userId",userInfo.getUserId());
        map.addAttribute("totalPage",customerInfoPage.getTotalPages());
        map.addAttribute("currentPage",page);

        //判断当前用户是否为咨询师
        if(userId == null){
            url = "/customerTrace/customerTraceList";
        }else{
            if(userInfo.getRoleId()== 2){//咨询师
                url = "/customerTrace/customerTraceList";
            }else{
                url = "/customerTrace/allCustomerTraceList";
            }
        }
        return url;
    }

    /**
     * 获取要跟进的客户相关信息
     * @param userId
     * @return
     */
    @RequestMapping("/customerTrace/listData")
    @ResponseBody
    protected  List<CustomerInfo> listData(String userId,String page,String pageSize,Model map,HttpServletRequest request){
        HttpSession httpSession = request.getSession(false);
        UserInfo userInfo = (UserInfo)httpSession.getAttribute("userinfo");
        Map<String,String> paraMap = new HashMap<String, String>();
        paraMap.put("userId",userInfo.getUserId()+"");
        if(page == null || "".equals(page)){
            page = "1";
        }
        if(pageSize == null || "".equals(pageSize)){
            pageSize = "5";
        }
        Pageable pageable = new PageRequest(Integer.parseInt(page)-1,Integer.parseInt(pageSize));
        Page<CustomerInfo> customerInfoPage = customerInfoService.pageCustomerTrace(paraMap,pageable);

       // List<CustomerInfo> customerInfos = customerInfoService.showCustomerTrace("1001");
        //将数据保存到request域中
        map.addAttribute("customerTraceList",customerInfoPage.getContent());
        map.addAttribute("userId",userInfo.getUserId()+"");
        map.addAttribute("totalPage",customerInfoPage.getTotalPages());
        return customerInfoPage.getContent();
    }


    /**
     * 获取跟进记录信息
     * @param customerId
     * @param userId
     * @return
     */
    @RequestMapping("/customerTrace/traceInfo")
    @ResponseBody
    protected  List<CustomerTraceRecordVo> traceInfo(String customerId,String userId){
        Map<String,String> map = new HashMap<String, String>();
        map.put("customerId",customerId);
        map.put("userId",userId);
        List<CustomerTraceRecordVo> list = customerTraceService.searchTraceRecord(map);
        return list;
    }

    /**
     * 添加跟进记录
     * @param customerTraceRecord
     * @return
     */
    @RequestMapping("/customerTrace/addTraceRecord")
    @ResponseBody
    protected EasyuiMessager addTraceRecord(CustomerTraceRecord customerTraceRecord){
        EasyuiMessager easyuiMessager = new EasyuiMessager();
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            customerTraceRecord.setRecordDate(simpleDateFormat.format(new Date()));//跟进时间
            customerTraceService.addTraceRecord(customerTraceRecord);
            easyuiMessager.setMsg("添加成功!");
            easyuiMessager.setSuccess(true);
        }catch (Exception e){
            easyuiMessager.setMsg("添加失败!");
            easyuiMessager.setSuccess(false);
        }
        return easyuiMessager;
    }

    /**
     * 获取所有客户跟进记录
     * @return
     */
    @RequestMapping("/customerTrace/allCustomerTrace")
    @ResponseBody
    protected String allCustomerTrace(){
        return null;
    }

    /**
     * 单个客户跟进情况
     * @param customerInfo
     * @param map
     * @return
     */
    @RequestMapping("/customerTrace/viewCustomerTrace")
    protected String viewCustomerTrace(CustomerInfo customerInfo,Model map){
        Map<String,String> paramMap = new HashMap<String, String>();
        paramMap.put("customerId",customerInfo.getCustomerId()+"");
        List<CustomerTraceRecord> customerTraceRecordList = customerTraceService.searchCustomerTraceRecordByParams(paramMap);
        map.addAttribute("customerTraceRecordList",customerTraceRecordList);
        map.addAttribute("customerId",customerInfo.getCustomerId());
        return "/customerTrace/viewCustomerTrace";
    }


    @RequestMapping("/customerTrace/ThirdCustomerTrace")
    @ResponseBody
    protected List<CustomerTraceRecord> thirdCustomerTrace(String customerId,HttpServletRequest request){
        Map<String,String> customerIdParamMap = new HashMap<String, String>();
        //根据咨询师编号和客户编号获取前三条跟进记录
        HttpSession httpSession = request.getSession(false);
        UserInfo userInfo = (UserInfo)httpSession.getAttribute("userinfo");
        Map<String,String> customerTraceMap = new HashMap<String, String>();
        customerIdParamMap.put("userId",userInfo.getUserId()+"");//咨询师编号
        customerIdParamMap.put("customerId",customerId);
        Pageable pageable = new PageRequest(0,3);
        Page<CustomerTraceRecord> page = customerTraceService.
                searchCustomerTraceRecordByParams(customerIdParamMap,pageable);
        List<CustomerTraceRecord> customerTraceRecordList = page.getContent();
        //map.addAttribute("customerTraceRecordList",customerTraceRecordList);
        return  customerTraceRecordList;
    }
}
