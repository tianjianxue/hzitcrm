package com.hzit.crm.web;

import com.fc.platform.commons.page.PageRequest;
import com.fc.platform.commons.page.Pageable;
import com.hzit.crm.core.entity.*;
import com.hzit.crm.service.*;
import com.hzit.crm.util.SHAUtil;
import com.hzit.crm.util.StringUtil;
import com.hzit.crm.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

/**
 * 类描述:咨询师访问控制器
 * Created by 冼耀基 on 2016/9/21.
 */
@Controller
public class UserInfoController extends  BaseController{
    private Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CustomerTraceService customerTraceService;
    /**
     * 咨询师的客户客户列表
     * @return
     */
    @RequestMapping("/userInfo/customerInfoList")
    protected String customerInfoList(String userId){
        //根据咨询师的id找到属于该咨询师的客户
       return "/userInfo/customerInfoList";
    }

    /**
     *获取数据
     * @param rows
     * @param page
     * @param sort
     * @param order
     * @param request
     * @return
     */
    @RequestMapping("/userInfo/customerInfo")
    @ResponseBody
    protected DataGrid<CustomerInfoVo> customerInfo(String rows, String page, String sort, String order, HttpServletRequest request){
        HttpSession httpSession = request.getSession(false);
        UserInfo userInfo = (UserInfo)httpSession.getAttribute("userinfo");
        /**
         * 默认每页20条数据
         */
        if(rows == null || "".equals(rows)){
            rows = "20";
        }
        if(page == null || "".equals(page)){
            page = "0";
        }
        Map<String,String> map = new HashMap<String, String>();
        map.put("userId",userInfo.getUserId()+"");
        Pageable pageable = new PageRequest(Integer.parseInt(page)-1,Integer.parseInt(rows),sort(sort,order));
        DataGrid<CustomerInfoVo> customerInfoVoList = userInfoService.customerInfoList(map,pageable);
        if(userInfo.getRoleId() == 2 || userInfo.getRoleId() == 3){//当用户角色为咨询师时
            Iterator<CustomerInfoVo> customerInfoVoIterator = customerInfoVoList.getRows().iterator();
            while(customerInfoVoIterator.hasNext()){
                CustomerInfoVo customerInfoVo =customerInfoVoIterator.next();
                if(customerInfoVo.getCustomerState() ==7){//客户状态为无效时
                    long data = customerInfoVoList.getTotal();
                    data--;
                    customerInfoVoList.setTotal(data);
                    customerInfoVoIterator.remove();
                }
            }
        }
        return customerInfoVoList;
    }

    /**
     * 我的客户排序
     * @param sort
     * @param order
     * @return
     */
    private String sort(String sort,String order){
        String[] strArr = {"real_name","sex","age","native_place","tel","wechat_no","qq","education_bg",
                "graduate_time","graduate_from","major_in","work_age","work_experience","job","educate_experience",
                "recruit_channel","customer_state","customer_level","user_id","target_skill","introducer","memo","last_time","create_time"};
        String sortName = "user_id ";
        if(order == null){
            order = "ASC";
        }
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
     * 员工信息列表
     * @return
     */
    @RequestMapping("/userInfo/list")
    protected String list(){
        return "/userInfo/userInfoList";
    }

    /**
     * 获取json数据(公司所有员工数据)
     * @return
     */
    @RequestMapping("/userInfo/listData")
    @ResponseBody
    protected DataGrid<UserInfoVo> listData(String page, String rows,UserInfo userInfo){
        Pageable pageable = null;
        if(rows == null || "".equals(rows)){
            rows="20";
        }
        if(page == null || "".equals(page)){
            page="1";
        }
        pageable = new PageRequest(Integer.parseInt(page)-1,Integer.parseInt(rows));
        Map<String,String> map = new HashMap<String, String>();
        if(StringUtil.isNotEmpty(userInfo.getName())){
            map.put("name",userInfo.getName());
        }
        DataGrid<UserInfoVo> dataGrid = userInfoService.searchUserInfoByParamsLike(map,pageable);
       // return userInfoService.userInfoList(map,pageable);
        return userInfoService.searchUserInfoByParamsLike(map,pageable);
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping("/userInfo/addui")
    protected String addui(Model map){
        //获取部门和公司信息
        List<Dept> deptList = deptService.searchDeptByParams(null);
        List<Company> companyList = companyService.searchCompanyByParams(null);
        map.addAttribute("deptList",deptList);
        map.addAttribute("companyList",companyList);
        return "/userInfo/addUserInfo";
    }

    /**
     * 处理添加员工请求
     * @param userInfo
     * @return
     */
    @RequestMapping("userInfo/addUserInfo")
    @ResponseBody
    protected EasyuiMessager add(UserInfo userInfo){
        userInfo.setRoleId(0);
        userInfo.setPassword("123456");//默认密码为123456
        EasyuiMessager easyuiMessager = new EasyuiMessager();
        try{
            userInfoService.save(userInfo);
            easyuiMessager.setMsg("添加成功!");
            easyuiMessager.setSuccess(true);
        }catch (Exception e){
            System.out.println(e.getMessage());
            easyuiMessager.setSuccess(false);
            easyuiMessager.setMsg("添加失败!");
        }
        return easyuiMessager;
    }

    /**
     * 跳转到修改页面
     * @return
     */
    @RequestMapping("/userInfo/editui")
    protected String editui(UserInfo userInfo, Model map){
        //根据id查找用户信息
        Map<String,String> paramMap = new HashMap<String, String>();
        paramMap.put("userId",userInfo.getUserId()+"");
        List<UserInfo> userInfoList = userInfoService.searchCustomerInfoByParams(paramMap);
        //获取部门和公司信息
        List<Dept> deptList = deptService.searchDeptByParams(null);
        List<Company> companyList = companyService.searchCompanyByParams(null);
        map.addAttribute("deptList",deptList);
        map.addAttribute("companyList",companyList);
        if(userInfoList != null && userInfoList.size() >0){
            map.addAttribute("userInfo",userInfoList.get(0));
        }else{
            map.addAttribute("userInfo",null);
        }
        return "/userInfo/editUserInfo";
    }

    /**
     * 处理修改数据
     * @param userInfo
     * @return
     */
    @RequestMapping("/userInfo/edit")
    @ResponseBody
    protected EasyuiMessager eidt(UserInfo userInfo){
        EasyuiMessager easyuiMessager = new EasyuiMessager();
        try{
            userInfoService.update(userInfo);
            easyuiMessager.setSuccess(true);
            easyuiMessager.setMsg("修改成功!");
        }catch (Exception e){
            e.printStackTrace();
            logger.debug(e.getMessage());
            easyuiMessager.setMsg("修改失败！");
            easyuiMessager.setSuccess(false);
        }
        return easyuiMessager;
    }

    /**
     * 删除数据
     * @param userInfo
     * @return
     */
    @RequestMapping("/userInfo/removeUserInfo")
    protected EasyuiMessager removeUserInfo(UserInfo userInfo){
        EasyuiMessager easyuiMessager = new EasyuiMessager();
        try{
            easyuiMessager.setSuccess(true);
            easyuiMessager.setMsg("删除成功！");
        }catch (Exception e){
            easyuiMessager.setSuccess(false);
            easyuiMessager.setMsg("删除失败！");
        }
        //userInfoService.
        return easyuiMessager;
    }

    /**
     * 跳转到添加角色页面
     * @return
     */
    @RequestMapping("/userInfo/addRoleUI")
    protected String addRoleUI(String userId,Model map){
        //获取所有的角色信息
        List<Role> roleList = roleService.searchRole(null);
        map.addAttribute("roleList",roleList);
        //获取该用户
        Map<String,String> paramMap = new HashMap<String, String>();
        paramMap.put("userId",userId);
        List<UserInfo> userInfoList = userInfoService.searchCustomerInfoByParams(paramMap);
        if(userInfoList != null && userInfoList.size() > 0){
            map.addAttribute("userInfo",userInfoList.get(0));
        }
        return "/userInfo/addRole";
    }

    /**
     * 分配角色
     * @param userInfo
     * @return
     */
    @RequestMapping("/userInfo/addRole")
    @ResponseBody
    protected EasyuiMessager addRole(UserInfo userInfo){
        EasyuiMessager easyuiMessager = new EasyuiMessager();
        try{
            userInfoService.update(userInfo);
            easyuiMessager.setMsg("分配角色成功！");
            easyuiMessager.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            easyuiMessager.setMsg("分配角色失败！");
            easyuiMessager.setSuccess(false);
        }
        return easyuiMessager;
    }

    /**
     * 咨询师看当前已经报名的信息
     * @return
     */
    @RequestMapping("/userInfo/customerState")
    protected String customerState(){
        return "/userInfo/customerState";
    }

    /**
     * 获取所有已经报名的客户信息
     * @return
     */
    @RequestMapping("/userInfo/customerStateList")
    @ResponseBody
    protected  DataGrid<CustomerInfoVo> customerStateList(EasyuiEntity easyuiEntity,CustomerInfo customerInfo,HttpServletRequest request) throws IOException {
        if(!StringUtil.isNotEmpty(easyuiEntity.getRows())){
            easyuiEntity.setRows("20");
        }
        if(!StringUtil.isNotEmpty(easyuiEntity.getPage())){
            easyuiEntity.setPage("1");
        }
        if(easyuiEntity.getOrder() == null){
            easyuiEntity.setOrder("asc");
        }

        //排序
        PageRequest pageRequest = new PageRequest(
                Integer.parseInt(easyuiEntity.getPage())-1,
                Integer.parseInt(easyuiEntity.getRows()),
                sort(easyuiEntity.getSort(),easyuiEntity.getOrder()));

        HttpSession httpSession = request.getSession(false);//获取httpSession对象
        UserInfo userInfo = (UserInfo)httpSession.getAttribute("userinfo");
        Map<String,String> paramMap = new HashMap<String, String>();
        //从配置文件中获取客户状态
        Properties properties = new Properties();
        File file = ResourceUtils.getFile("classpath:userInfo.properties");
        InputStream inputStream = new FileInputStream(file);
        properties.load(inputStream);
        //客户状态
        paramMap.put("customerState",(String)properties.get("customerState"));
        //客户编号
        paramMap.put("userId",userInfo.getUserId()+"");

        if(StringUtil.isNotEmpty(customerInfo.getRealName())){
            paramMap.put("realName",customerInfo.getRealName());
        }else if(StringUtil.isNotEmpty(customerInfo.getNativePlace())){
            paramMap.put("nativePlace",customerInfo.getNativePlace());
        }else if(StringUtil.isNotEmpty(customerInfo.getTel())){
            paramMap.put("tel",customerInfo.getTel());
        }

        DataGrid<CustomerInfoVo> dataGrid = userInfoService.customerInfoList(paramMap,pageRequest);
        //DataGrid<CustomerInfoVo> dataGrid = customerInfoService.searchCustomerInfoByParamsLike(paramMap,pageRequest);
        return dataGrid;
    }

    /**
     * 获取分公司已经报名的客户
     * @param easyuiEntity
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/userInfo/allCustomerStateList")
    @ResponseBody
    protected DataGrid<CustomerInfoVo> allCustomerStateList(EasyuiEntity easyuiEntity,CustomerInfo customerInfo,HttpServletRequest request) throws IOException {
        if(!StringUtil.isNotEmpty(easyuiEntity.getRows())){
            easyuiEntity.setRows("20");
        }
        if(!StringUtil.isNotEmpty(easyuiEntity.getPage())){
            easyuiEntity.setPage("1");
        }
        if(easyuiEntity.getOrder() == null){
            easyuiEntity.setOrder("asc");
        }
        //排序
        PageRequest pageRequest = new PageRequest(
                Integer.parseInt(easyuiEntity.getPage())-1,
                Integer.parseInt(easyuiEntity.getRows()),
                sort(easyuiEntity.getSort(),easyuiEntity.getOrder()));

        Map<String,String> paramMap = new HashMap<String, String>();
        //从配置文件中获取客户状态
        Properties properties = new Properties();
        File file = ResourceUtils.getFile("classpath:userInfo.properties");
        InputStream inputStream = new FileInputStream(file);
        properties.load(inputStream);
        //客户状态
        paramMap.put("customerState",(String)properties.get("customerState"));
        //获取公司编号
        HttpSession httpSession = request.getSession(false);
        UserInfo userInfo = (UserInfo)httpSession.getAttribute("userinfo");
        if(userInfo.getRoleId() != 11){//当前用户角色不为总经理时,只能获取分公司所有已经报名的客户
            paramMap.put("companyId",userInfo.getCompanyId()+"");//公司编号
        }
        if(StringUtil.isNotEmpty(customerInfo.getRealName())){
            paramMap.put("realName",customerInfo.getRealName());
        }else if(StringUtil.isNotEmpty(customerInfo.getTel())){
            paramMap.put("tel",customerInfo.getTel());
        }else if(StringUtil.isNotEmpty(customerInfo.getNativePlace())){
            paramMap.put("nativePlace",customerInfo.getNativePlace());
        }
        DataGrid<CustomerInfoVo> dataGrid = userInfoService.customerInfoList(paramMap,pageRequest);
        return dataGrid;
    }

    /**
     * (咨询主管使用)获取所有的咨询师跟进记录
     * @return
     */
    @RequestMapping("/userInfo/allCustomerTrace")
    protected String allCustomerTrace(){
        return "/customerTrace/allCustomerTraceList";
    }

    /**
     * 获取所有咨询师的信息(列表)
     * @return
     */
    @RequestMapping("/userInfo/consultantList")
    protected String consultantList(){
        return "/userInfo/consultantList";
    }

    /**
     * 获取所有咨询师信息(数据)
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/userInfo/consultantListData")
    @ResponseBody
    protected DataGrid<UserInfoVo> consultantListData(String page, String rows, String sort, String order,HttpServletRequest request) throws IOException {
        Map<String,String> paramMap = new HashMap<String, String>();
        //从配置文件中获取客户状态
        Properties properties = new Properties();
        File file = ResourceUtils.getFile("classpath:userInfo.properties");
        InputStream inputStream = new FileInputStream(file);
        properties.load(inputStream);
        paramMap.put("roleId",properties.get("roleId")+"");//获取角色编号为2的员工
        //分公司咨询师
        HttpSession httpSession = request.getSession(false);
        UserInfo userInfo = (UserInfo)httpSession.getAttribute("userinfo");
        if(userInfo.getRoleId() != 11){
            paramMap.put("companyId",userInfo.getCompanyId()+"");
        }
        if(rows == null || "".equals(rows)){
            rows="20";
        }
        if(page == null || "".equals(page)){
            page="1";
        }
        if(order == null){
            order = "asc";
        }
        //排序
        PageRequest pageRequest = new PageRequest(Integer.parseInt(page)-1,Integer.parseInt(rows),sort(sort,order));
        return userInfoService.userInfoList(paramMap,pageRequest);
    }
    /**
     * 用户修改密码
     * @return
     */
    @RequestMapping(value = "/userInfo/changePassword",method = RequestMethod.GET)
    protected String changePassword(){

        return "/userInfo/changePassword";
    }

    /**
     * 处理修改密码请求
     * @param password
     * @param pwd
     * @param request
     * @return
     */
    @RequestMapping(value = "/userInfo/changePwd",method = RequestMethod.POST)
    protected String changePwd(String password,String pwd,HttpServletRequest request,Model map) throws Exception {
        String url = "";
        HttpSession httpSession = request.getSession(false);
        UserInfo userInfo = (UserInfo)httpSession.getAttribute("userinfo");
        Map<String,String> pwdParamMap = new HashMap<String, String>();
        pwdParamMap.put("userId",userInfo.getUserId()+"");
        List<UserInfo> userInfoList = userInfoService.searchCustomerInfoByParams(pwdParamMap);
        if(userInfoList != null && userInfoList.size() >0){
            UserInfo userInfo1 = userInfoList.get(0);
            if(SHAUtil.shaEncode(password).equals(userInfo1.getPassword())){//修改密码
                userInfo.setPassword(SHAUtil.shaEncode(pwd));
                userInfoService.update(userInfo);
                url = "/foreground/index";
            }else{
                map.addAttribute("msg","旧密码不正确!");
                url = "/userInfo/changePassword";
            }
        }
        return url;
    }

    /**
     * 咨询师客户跟进详情
     * @param customerInfo
     * @return
     */
   @RequestMapping("/userInfo/detailedCustomerTrace")
    protected  String detailedCustomerTrace(CustomerInfo customerInfo,Model map){
        map.addAttribute("customerTraceRecordVoList",customerTraceService.detailedCustomerTrace(customerInfo));
        return "/customerTrace/detailedCustomerTrace";
    }

    /**
     * 查看客户报名情况(咨询主管以上级别使用)
     * @param userInfo
     * @return
     */
    @RequestMapping("/userInfo/viewCustomerState")
    protected String viewCustomerState(UserInfo userInfo,Model map) throws IOException {
        Map<String,String> paramMap = new HashMap<String, String>();
        paramMap.put("userId",userInfo.getUserId()+"");
        //从配置文件中获取客户状态
        Properties properties = new Properties();
        File file = ResourceUtils.getFile("classpath:userInfo.properties");
        InputStream inputStream = new FileInputStream(file);
        properties.load(inputStream);
        paramMap.put("customerState","5");//客户为已报名状态
        List<CustomerInfo> customerInfoList = customerInfoService.searchCustomerInfoByParams(paramMap);
        map.addAttribute("customerInfoList",customerInfoList);
        map.addAttribute("total",customerInfoList.size());
        return "/userInfo/viewCustomerState";
    }

    /**
     * 用户重置密码
     * @param userInfo
     * @return
     * @throws Exception
     */
    @RequestMapping("/userInfo/resetPassword")
    @ResponseBody
    protected String resetPassword(UserInfo userInfo) throws Exception {
        String msg = "";
        userInfo.setPassword(SHAUtil.shaEncode("123456"));//重置密码
        try{
            userInfoService.update(userInfo);
            msg = "true";
        }catch (Exception e){
            msg = "false";
        }
        return msg;
    }

}
