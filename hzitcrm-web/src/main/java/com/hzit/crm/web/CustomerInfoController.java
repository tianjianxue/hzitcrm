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
import com.hzit.crm.service.UserInfoService;
import com.hzit.crm.util.IntegerUtil;
import com.hzit.crm.util.StringUtil;
import com.hzit.crm.vo.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by 冼耀基 on 2016/9/20.
 */

@Controller
public class CustomerInfoController extends  BaseController{
    private List<CustomerInfoVo> customerInfoVoList;
    @Autowired
    private CustomerInfoService customerInfoService;
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private CustomerStateService customerStateService;

    @Autowired
    private CustomerTraceService customerTraceService;
    /**
     * 获取客户信息(主页来访列表用)
     * @return
     */
    @RequestMapping("/customerInfoList")
    @ResponseBody
    protected List<CustomerInfoVo> customerInfoList(){
        return customerInfoService.findByNameAndState();
    }


    /**
     * 前台人员修改客户所属咨询师
     * @return
     */
    @RequestMapping("/customerInfo/changeUser")
    @ResponseBody
    protected String changeUser(CustomerInfo customerInfo){
        String msg;
        try{
            msg ="true";
            customerInfoService.updateCustomerInfo(customerInfo);
        }catch (Exception e){
            msg = "false";
        }
        return msg;
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
    protected DataGrid<CustomerInfoVo> ajaxList(EasyuiEntity easyuiEntity, CustomerInfo customerInfo,
                                                String currentUser,String exportCustomer, HttpServletRequest request,
                                                HttpServletResponse response) throws IOException, ClassNotFoundException {
        HttpSession httpSession = request.getSession(false);
        UserInfo userInfo = (UserInfo)httpSession.getAttribute("userinfo");
        commonEasyuiEntity(easyuiEntity);
        //排序
        PageRequest pageRequest = new PageRequest(
                Integer.parseInt(easyuiEntity.getPage())-1,
                Integer.parseInt(easyuiEntity.getRows()),
                sort(easyuiEntity.getSort(),easyuiEntity.getOrder()));
        //搜索
        Map<String,String> paramMap = new HashMap<String, String>();
        pramCommon(customerInfo, paramMap,userInfo,currentUser);
        DataGrid<CustomerInfoVo> dataGrid =customerInfoService.searchCustomerInfoByParamsLike(paramMap,pageRequest);
       /* if (exportCustomer != null){
            this.exportCustomerInfo(dataGrid.getRows(),response);
        }*/
        customerInfoVoList = dataGrid.getRows();
        return  dataGrid;
    }

    private void commonEasyuiEntity(EasyuiEntity easyuiEntity) {
        if(easyuiEntity.getRows() == null || "".equals(easyuiEntity.getRows())){
            easyuiEntity.setRows("20");
        }
        if(!StringUtil.isNotEmpty(easyuiEntity.getPage())){
           easyuiEntity.setPage("1");
        }
        if(easyuiEntity.getOrder() == null){
            easyuiEntity.setOrder("asc");
        }
    }

    /**
     * 搜索条件
     * @param customerInfo
     * @param paramMap
     */
    private void pramCommon(CustomerInfo customerInfo, Map<String, String> paramMap,UserInfo userInfo,String currentUser) {
        //获取客户信息
        //HttpSession httpSession = request.getSession(false);
        //UserInfo userInfo = (UserInfo)httpSession.getAttribute("userinfo");
        if(StringUtil.isNotEmpty(customerInfo.getRealName())){
            paramMap.put("realName",customerInfo.getRealName());
        }else if(StringUtil.isNotEmpty(customerInfo.getNativePlace())){
            paramMap.put("nativePlace",customerInfo.getNativePlace());
        }else if(StringUtil.isNotEmpty(customerInfo.getTel())){
            paramMap.put("tel",customerInfo.getTel());
        }else if(StringUtil.isNotEmpty(customerInfo.getWechatNo())){
            paramMap.put("wechartNo",customerInfo.getWechatNo());
        }else if(StringUtil.isNotEmpty(customerInfo.getGraduateFrom())){
            paramMap.put("graduateFrom",customerInfo.getGraduateFrom());
        }else if(StringUtil.isNotEmpty(customerInfo.getCreateTime())){
            paramMap.put("createTime",customerInfo.getCreateTime());
        }
        if(customerInfo.getCustomerState()!=null){//学员状态
            paramMap.put("customerState",customerInfo.getCustomerState()+"");
        }
        if(customerInfo.getCustomerLevel() != null){//学员级别
            paramMap.put("customerLevel",customerInfo.getCustomerLevel()+"");
        }
        if(userInfo.getRoleId() ==2){
            paramMap.put("userId",userInfo.getUserId()+"");
        }

        if(userInfo.getRoleId()==3){//当员工为咨询主管时

            if(currentUser != null){
                paramMap.put("userId",userInfo.getUserId()+"");
            }
        }
        //获取用户的角色编号
        if(userInfo.getRoleId()!=11){//当用户角色为经理时
            paramMap.put("companyId",userInfo.getCompanyId()+"");
        }
        //if(userInfo.get)
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
    protected String editCustomerInfoui(String customerId,Model map,HttpServletRequest request){
        common(customerId, map, request);

        return "/customer/editCustomerInfo";
    }

    private void common(String customerId, Model map, HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        UserInfo userInfo = (UserInfo)httpSession.getAttribute("userinfo");
        Map<String,String> paramMap = new HashMap<String, String>();
        paramMap.put("companyId",userInfo.getCompanyId()+"");
        //获取所有的面试官信息
        map.addAttribute("customerInfo",customerInfoService.findCustomerInfoById(customerId));
        List<UserInfo> userInfoList = userInfoService.consultantList(paramMap);
        map.addAttribute("userInfoList",userInfoList);
        //获取客户状态信息
        List<CustomerState> customerStateList = customerStateService.searchCustomerStateByParams(null);
        map.addAttribute("customerStateList",customerStateList);



        //获取所有的邀约人
        List<UserInfo> yaoYueRenList = userInfoService.getAllYaoYueRen(paramMap);
        map.addAttribute("yaoYueRenList",yaoYueRenList);
    }


    /**
     * 保存修改后的客户信息
     */
    @RequestMapping("/customer/editCustomerInfo")
    @ResponseBody
    private EasyuiMessager editCustomerInfo(CustomerInfo customerInfo) throws InterruptedException {
        //customerInfo.setCustomerState(2);//客户试听
        EasyuiMessager easyuiMessager = new EasyuiMessager();
        try{
            customerInfoService.updateCustomerInfo(customerInfo);
            easyuiMessager.setMsg("修改成功!");
            easyuiMessager.setSuccess(true);
        }catch (Exception e){
            easyuiMessager.setMsg("修改失败!");
            easyuiMessager.setSuccess(false);
        }
        //模拟网络堵塞
        //Thread.sleep(1000);
        return easyuiMessager;
    }

    /**
     * 跳转到报表导出页面
     * @return
     */
    @RequestMapping("/customerInfo/exportCustomerInfoui")
    protected String exportCustomerInfoui(){
        return "/export/exportCustomerInfo";
    }
    /**
     * 报表导出
     */
    @RequestMapping("/customerInfo/exportCustomerInfo")
    protected void exportCustomerInfo(HttpServletResponse response) throws IOException, ClassNotFoundException {
        if(customerInfoVoList == null || customerInfoVoList.size() <=0){
            return ;
        }
        //准备一个数组
        //声明一个工作簿
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //声明一个表格
        HSSFSheet hssfSheet = hssfWorkbook.createSheet();
        HSSFRow hssfRow = hssfSheet.createRow(0);
        HSSFCell hssfCell = null;
        //准备一个数组
        String[] strArr = {"编号","学员姓名","性别","年龄","籍贯","联系电话","微信号","qq号","学历","毕业时间",
                 "毕业院校","专业","工作年限","工作经历","从事职业","教育培训经历","应聘渠道","客户状态",
                 "客户级别","咨询师","客户感兴趣的目标技能","邀约人","备注","创建时间","最后跟进时间"};
        for(int i=0;i<strArr.length;i++){
            hssfCell = hssfRow.createCell(i);
            hssfCell.setCellValue(strArr[i]);
        }
        //导出数据
        //查找所有的客户信息数据
        HSSFRow nRow;
        HSSFCell nCell;
        for(int i=0; i < customerInfoVoList.size();i++){ //循环遍历数据
          CustomerInfoVo  customerInfoVo = customerInfoVoList.get(i);
          nRow = hssfSheet.createRow(i+1);
          nCell = nRow.createCell(0);
          nCell.setCellValue(i+1);//学员编号

          nCell = nRow.createCell(1);
          nCell.setCellValue(StringUtil.isNotEmptyAndNull(customerInfoVo.getRealName()));//姓名

          nCell = nRow.createCell(2);
            if(IntegerUtil.isNotNull(customerInfoVo.getSex())==1){
                nCell.setCellValue("男");//性别
            }else if(IntegerUtil.isNotNull(customerInfoVo.getSex())==2){
                nCell.setCellValue("女");//性别
            }

          nCell = nRow.createCell(3);
          nCell.setCellValue(IntegerUtil.isNotNull(customerInfoVo.getAge()));//年龄

          nCell = nRow.createCell(4);
          nCell.setCellValue(StringUtil.isNotEmptyAndNull(customerInfoVo.getNativePlace()));//籍贯

          nCell = nRow.createCell(5);
          nCell.setCellValue(StringUtil.isNotEmptyAndNull(customerInfoVo.getTel()));//联系电话

          nCell = nRow.createCell(6);
          nCell.setCellValue(StringUtil.isNotEmptyAndNull(customerInfoVo.getWechatNo()));//微信号

          nCell = nRow.createCell(7);
          nCell.setCellValue(StringUtil.isNotEmptyAndNull(customerInfoVo.getQq()));//qq号

          nCell = nRow.createCell(8);
            if(IntegerUtil.isNotNull(customerInfoVo.getEducationBg()) ==1){
                nCell.setCellValue("小学");
            }else if(IntegerUtil.isNotNull(customerInfoVo.getEducationBg()) ==2){
                nCell.setCellValue("初中");
            }else if(IntegerUtil.isNotNull(customerInfoVo.getEducationBg()) ==3){
                nCell.setCellValue("高中");
            }else if(IntegerUtil.isNotNull(customerInfoVo.getEducationBg()) ==4){
                nCell.setCellValue("中专");
            }else if(IntegerUtil.isNotNull(customerInfoVo.getEducationBg()) ==5){
                nCell.setCellValue("大专");
            }else if(IntegerUtil.isNotNull(customerInfoVo.getEducationBg()) ==6){
                nCell.setCellValue("本科");
            }else if(IntegerUtil.isNotNull(customerInfoVo.getEducationBg()) ==7){
                nCell.setCellValue("研究生");
            }else if(IntegerUtil.isNotNull(customerInfoVo.getEducationBg()) ==8){
                nCell.setCellValue("其他");
            }

              nCell = nRow.createCell(9);
              nCell.setCellValue(StringUtil.isNotEmptyAndNull(customerInfoVo.getGraduateTime()));//毕业时间

              nCell = nRow.createCell(10);
              nCell.setCellValue(StringUtil.isNotEmptyAndNull(customerInfoVo.getGraduateFrom()));//毕业学校

              nCell = nRow.createCell(11);
              nCell.setCellValue(StringUtil.isNotEmptyAndNull(customerInfoVo.getMajorIn()));//专业

              nCell = nRow.createCell(12);
              nCell.setCellValue(IntegerUtil.isNotNull(customerInfoVo.getWorkAge()));//工作年限

              nCell = nRow.createCell(13);
              nCell.setCellValue(StringUtil.isNotEmptyAndNull(customerInfoVo.getWorkExperience()));//工作经历

              nCell = nRow.createCell(14);
              nCell.setCellValue(StringUtil.isNotEmptyAndNull(customerInfoVo.getJob()));//从事职业

              nCell = nRow.createCell(15);
              nCell.setCellValue(StringUtil.isNotEmptyAndNull(customerInfoVo.getEducateExperience()));//教育培训经历


            nCell = nRow.createCell(16);
            if(IntegerUtil.isNotNull(customerInfoVo.getRecruitChannel()) ==1){
                nCell.setCellValue("智联");//应聘渠道
            }else if(IntegerUtil.isNotNull(customerInfoVo.getRecruitChannel())==2){
                nCell.setCellValue("前程无忧");//应聘渠道
            }else if(IntegerUtil.isNotNull(customerInfoVo.getRecruitChannel())==3){
                nCell.setCellValue("58同城");//应聘渠道
            }else if(IntegerUtil.isNotNull(customerInfoVo.getRecruitChannel())==4){
                nCell.setCellValue("转介绍");//应聘渠道
            }else if(IntegerUtil.isNotNull(customerInfoVo.getRecruitChannel())==5){
                nCell.setCellValue("中华英才");//应聘渠道
            }else{
                nCell.setCellValue("其他");//应聘渠道
            }

          nCell = nRow.createCell(17);
          nCell.setCellValue(StringUtil.isNotEmptyAndNull(customerInfoVo.getCustomerStateMsg()));//客户状态

          nCell = nRow.createCell(18);
          nCell.setCellValue(StringUtil.isNotEmptyAndNull(customerInfoVo.getCustomerLevel()));//客户级别

          nCell = nRow.createCell(19);
          nCell.setCellValue(StringUtil.isNotEmptyAndNull(customerInfoVo.getUserName()));//咨询师

          nCell = nRow.createCell(20);
          nCell.setCellValue(StringUtil.isNotEmptyAndNull(customerInfoVo.getTargetSkill()));//客户感兴趣的目标技能

          nCell = nRow.createCell(21);
          nCell.setCellValue(StringUtil.isNotEmptyAndNull(customerInfoVo.getIntroducer()));//邀约人

          nCell = nRow.createCell(22);
          nCell.setCellValue(StringUtil.isNotEmptyAndNull(customerInfoVo.getMemo()));//备注

          nCell = nRow.createCell(23);
          nCell.setCellValue(StringUtil.isNotEmptyAndNull(customerInfoVo.getCreateTime()));//创建时间


          nCell = nRow.createCell(24);
          nCell.setCellValue(StringUtil.isNotEmptyAndNull(customerInfoVo.getLastTime()));//最后跟进时间*/
        }
        String fileName = "学员信息导出.xls";
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("content-disposition","attachment;fileName="+new String(fileName.getBytes("gbk"),"iso-8859-1"));
        OutputStream outputStream = response.getOutputStream();
        hssfWorkbook.write(outputStream);
        outputStream.flush();//刷新缓存
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

    /**
     *
     * @return
     */
    @RequestMapping("/userInfo/alterCustomerState")
    @ResponseBody
    protected  String customerState(CustomerInfo customerInfo) throws IOException {
        //从配置文件中获取客户状态
        Properties properties = new Properties();
        File file = ResourceUtils.getFile("classpath:userInfo.properties");
        InputStream inputStream = new FileInputStream(file);
        properties.load(inputStream);
        customerInfo.setCustomerState(2);//已面试状态
        String msg = "";
        try{
            customerInfoService.updateCustomerInfo(customerInfo);
            msg = "操作成功!";
        }catch(Exception e){
            msg = "操作失败!";
        }
        return msg;
    }

    /**
     * 获取客户详情和客户跟进记录(最近三条)
     * @return
     */
    @RequestMapping("/customerInfo/detailedAndTraceCustomer")
    protected String detailedAndTraceCustomer(CustomerInfo customerInfo,Model map,HttpServletRequest request){
        Map<String,String> customerIdParamMap = new HashMap<String, String>();
        customerIdParamMap.put("customerId",customerInfo.getCustomerId()+"");
        List<CustomerInfo> customerInfoList = customerInfoService.searchCustomerInfoByParams(customerIdParamMap);
        if(customerInfoList != null && customerInfoList.size() >0){
            map.addAttribute("customerInfo",customerInfoList.get(0));
        }
        //获取状态数据
        List<CustomerState> customerStateList = customerStateService.searchCustomerStateByParams(null);
        map.addAttribute("customerStateList",customerStateList);
        return "/userInfo/detailedAndTraceCustomer";
    }

    /**
     * 跳转到学员编辑页
     * @return
     */
    @RequestMapping("/customerInfo/editIntroducer")
    protected String editIntroducer(String customerId,Model map,HttpServletRequest request){
        common(customerId, map, request);
        return  "/customer/editIntroducer";
    }


}
