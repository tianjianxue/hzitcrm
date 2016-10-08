package com.hzit.crm.web;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.PageRequest;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.core.entity.CustomerTraceRecord;
import com.hzit.crm.service.CustomerInfoService;
import com.hzit.crm.service.CustomerTraceService;
import com.hzit.crm.vo.CustomerTraceRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**类描述:客户跟进
 * Created by 冼耀基 on 2016/9/24.
 */
@Controller
public class CustomerTraceController {
    @Autowired
    private CustomerTraceService customerTraceService;
    @Autowired
    private CustomerInfoService customerInfoService;
    @RequestMapping("/customerTrace/list")
    protected String  list(String page,String pageSize,Model map){
        Map<String,String> paraMap = new HashMap<String, String>();
        paraMap.put("userId","1001");
        if(page == null || "".equals(page)){
            page = "1";
        }
        if(pageSize == null || "".equals(pageSize)){
            pageSize = "5";
        }
        Pageable pageable = new PageRequest(Integer.parseInt(page)-1,Integer.parseInt(pageSize));
        Page<CustomerInfo> customerInfoPage = customerInfoService.pageCustomerTrace(paraMap,pageable);

        List<CustomerInfo> customerInfos = customerInfoService.showCustomerTrace("1001");
        map.addAttribute("customerTraceList",customerInfoPage.getContent());
        map.addAttribute("userId","1001");
        map.addAttribute("totalPage",customerInfoPage.getTotalPages());

        return "/customerTrace/customerTraceList";
    }

    /**
     * 获取要跟进的客户相关信息
     * @param userId
     * @return
     */
    @RequestMapping("/customerTrace/listData")
    @ResponseBody
    protected  List<CustomerInfo> listData(String userId,String page,String pageSize,Model map){
        Map<String,String> paraMap = new HashMap<String, String>();
        paraMap.put("userId","1001");
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
        map.addAttribute("userId","1001");
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
    protected String addTraceRecord(CustomerTraceRecord customerTraceRecord){
        String msg;
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            customerTraceRecord.setRecordDate(simpleDateFormat.format(new Date()));//跟进时间
            customerTraceService.addTraceRecord(customerTraceRecord);
            msg = "true";
        }catch (Exception e){
            msg = "false";
        }
        return msg;
    }


}
