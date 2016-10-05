package com.hzit.crm.web;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.PageRequest;
import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.core.entity.UserInfo;
import com.hzit.crm.service.CustomerInfoService;
import com.hzit.crm.service.UserInfoService;
import com.hzit.crm.vo.CustomerInfoVo;
import com.hzit.crm.vo.DataGrid;
import com.hzit.crm.vo.EasyuiMessager;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 冼耀基 on 2016/9/20.
 */

@Controller
public class CustomerInfoController extends  BaseController{
    @Autowired
    private CustomerInfoService customerInfoService;
    @Autowired
    private UserInfoService userInfoService;
    /**
     * 获取客户信息(主页来访列表用)
     * @return
     */
    @RequestMapping("/customerInfoList")
    @ResponseBody
    protected List<CustomerInfo> customerInfoList(){
        return customerInfoService.findByNameAndState();
    }

    /**
     * 跳转到客户信息列表
     * @param map
     * @return
     */
    @RequestMapping("/customerInfo/list")
    protected  String list(Model map){
        /*PageRequest pageRequest = new PageRequest(0,100);
        Map<String,String> paramMap = null;
        Page<CustomerInfo> page = customerInfoService.customerInfoList(paramMap,pageRequest,);
        List<CustomerInfo> customerInfoList = null;
        if(page != null){
            customerInfoList = page.getContent();
        }
        map.addAttribute("customerInfoList",customerInfoList);*/
        return "/customer/customerList";
    }

    /**
     * 异步方式获取客户信息
     * @return
     */
    @RequestMapping("/customerInfo/ajaxList")
    @ResponseBody
    protected DataGrid<CustomerInfoVo> ajaxList(String page, String rows, String sort, String order){
        if(rows == null || "".equals(rows)){
            rows="20";
        }
        if(page == null || "".equals(page)){
            page="1";
        }
        if(order == null){
            order = "asc";
        }
        PageRequest pageRequest = new PageRequest(Integer.parseInt(page)-1,Integer.parseInt(rows),sort(sort,order));
        Map<String,String> paramMap = null;
        //Page<CustomerInfo> page2 = customerInfoService.customerInfoList(paramMap,pageRequest,sort,order);

        return  customerInfoService.customerInfoList(paramMap,pageRequest,sort,order);
    }

    /**
     * 服务器排序
     * @param sort
     * @return
     */
    private String sort(String sort,String order){
        String[] strArr = {"real_name","sex","age","native_place","tel","wechat_no","qq","education_bg",
                "graduate_time","graduate_from","major_in","work_age","work_experience","job","educate_experience",
        "recruit_channel","customer_state","customer_level","user_id","target_skill","introducer","memo","last_time","create_time"};
        String sortName = "create_time ";
        if(sort != null){
            for(String str : strArr){
                if(str.replace("_","").equals(sort.toLowerCase())){
                    sortName =  str;
                }
            }
        }
        return sortName+" "+order;
    }
    /**
     * 使用者(咨询师)
     * 跳转到录入客户信息详情表单页
     */
    @RequestMapping("/typedCustomerInfoui")
    protected  String typedCustomerInfoui(){
        return "/customer/typedCustomerInfo";
    }

    /**
     * 录入客户信息(咨询师修改完善客户信息)
      * @return
     */
    @RequestMapping("/typedCustomerInfo")
    protected  String typedCustomerInfo(CustomerInfo customerInfo ){
        return "index";
    }

    /**
     * 跳转到客户信息修改页面
     */
    @RequestMapping("/customer/editCustomerInfoui")
    protected String editCustomerInfoui(String customerId,Model map){
        //获取所有的面试官信息
        map.addAttribute("customerInfo",customerInfoService.findCustomerInfoById(customerId));
        List<UserInfo> userInfoList = userInfoService.findAll();
        map.addAttribute("userInfoList",userInfoList);
        return "/customer/editCustomerInfo";
    }

    /**
     * 保存修改后的客户信息
     */
    @RequestMapping("/customer/editCustomerInfo")
    @ResponseBody
    private EasyuiMessager editCustomerInfo(CustomerInfo customerInfo) throws InterruptedException {
        EasyuiMessager easyuiMessager = new EasyuiMessager();
        try{
            customerInfoService.updateCustomerInfo(customerInfo);
            easyuiMessager.setMsg("修改成功!");
            easyuiMessager.setSuccess(true);
        }catch (Exception e){
            easyuiMessager.setMsg("修改失败!");
            easyuiMessager.setSuccess(false);
        }
        //Thread.sleep(5000);//模拟网络堵塞（测试时用）

        return easyuiMessager;
    }

    /**
     * 报表导出
     */
    @RequestMapping("/exportCustomerInfo")
    protected void exprotCustomerInfo(HttpServletResponse response) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet();
        HSSFRow hssfRow1 = hssfSheet.createRow(0);
        HSSFCell hssfCell1 = hssfRow1.createCell(0);
        HSSFCell hssfCell2 = hssfRow1.createCell(1);
        //hssfCell.setCellType(1);
        hssfCell1.setCellValue("你好");
        hssfCell2.setCellValue("世界！！！");
        response.setHeader("content-disposition","attachment;fileName=a.xls");
        OutputStream outputStream = response.getOutputStream();
       // outputStream.write("hello".getBytes());
        hssfWorkbook.write(outputStream);
        outputStream.close();//关闭资源
    }

    /**
     * 报表导入
     */
    @RequestMapping("/importCustomreInfo")
    protected void importCustomerInfo(){

    }

    /**
     * 查看客户详情
     * @param customerId
     * @param map
     * @return
     */
    @RequestMapping("/customer/viewCustomerInfoui")
    protected String detail(String customerId,Model map){
        //获取所有的面试官信息
        map.addAttribute("customerInfo",customerInfoService.findCustomerInfoById(customerId));
        List<UserInfo> userInfoList = userInfoService.findAll();
        map.addAttribute("userInfoList",userInfoList);
        return "/customer/customerInfoDetail";
    }

}
