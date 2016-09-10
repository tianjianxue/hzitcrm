package com.hzit.crm.web;

import com.fc.platform.commons.page.PageRequest;

import com.hzit.crm.core.mapper.entity.CustomerInfo;
import com.hzit.crm.core.mapper.entity.CustomerTraceRecord;
import com.hzit.crm.service.CustomerService;
import com.hzit.crm.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 咨询师界面控制器
 * Created by Administrator on 2016/8/20.
 */
@Controller
public class ConsultantController
{
    private Logger log = LoggerFactory.getLogger(ConsultantController.class);

    @Autowired
    private CustomerService customerService;

    /**
     * 进入咨询师主界面,加载咨询师旗下客户列表
     * @praram consultantId 咨询师id
     * TODO 1.登陆时通过拦截器获取 2.Order by & 过滤   3.分页待确认(offset可否选择？)
     */
    @RequestMapping("/consultant")
    public String go(@RequestParam(name = "consultantId",required = false) String consultantId,ModelMap modelMap)
    {
        consultantId =1001+""; //FIXME  测试用 待解决参数传入为空问题！！！
        if(StringUtils.isNotBlank(consultantId))
        {
            // 1.查询出属于该咨询师的客户列表  CustomerInfo param,Page page
            CustomerInfo cInfo = new CustomerInfo();
            // * consultantId在DB里面表现为userId
            cInfo.setUserId(Integer.parseInt(consultantId));
            // * 去掉已经成交的客户！
//          cInfo.setCustomerState(CustomerStateEnum.SUCCESS.getStateCode());
            // * 加载初始页面10条
            PageRequest pageRequest = new PageRequest(0,10);
            Map<String,Object> resultMap = customerService.getCustomerInfoListByConsultantId(cInfo,pageRequest);
            log.info("咨询师Id="+consultantId+",客户列表为:customerInfoList="+resultMap);
            // * 推送数据给页面,包括数据和总页数
            modelMap.put("customerPageMap",resultMap);
        }
        return "consultant";
    }

    /**

     * 咨询师页面翻页 FIXME[其实可以合并，但是目前懒得重构]
     * @param consultantId
     * @param pageNo
     */
    @RequestMapping("/changePageForCustomerList")
    public Object getCustomerListByPage(@RequestParam String consultantId,
                                        @RequestParam String pageNo,
                                        ModelMap modelMap)
    {
        //页数控制[最少第一页]
        int pageNumnber =( StringUtils.isNotBlank(pageNo) && Integer.parseInt(pageNo)>=1 ) ? Integer.parseInt(pageNo) : 1;
        PageRequest pageRequest = new PageRequest(pageNumnber-1,10);    //程序中页数默认是从0开始的
        CustomerInfo cInfo = new CustomerInfo();
        cInfo.setUserId(Integer.parseInt(consultantId));
        Map<String,Object> resultMap = customerService.getCustomerInfoListByConsultantId(cInfo,pageRequest);
        modelMap.put("customerPageMap",resultMap);

        return "consultant";
    }


    /**
     * 添加访客跟进记录
     * TODO 先写死为 userId=1001的咨询师旗下，后续要改进前端获取
     * @param consultantId  TODO 从session中获取登陆用户的信息，有待完善！！
     * @return
     */
    @RequestMapping("/addCustomerTraceItem")
    public String addCustomerTraceItem(@RequestParam(name="consultantId",required =false) String consultantId,
                                       @RequestParam(name="customerId",  required =false) String customerId,
                                       @RequestParam("txtjilu")                           String content,
                                       ModelMap modelMap)
    {
        Integer custId = StringUtils.isBlank(customerId)? null : Integer.parseInt(customerId);
        System.err.println("客户的id="+customerId);

        //获取当前时间格式为:yyyy-MM-dd HH:mm:ss
        String nowTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
        CustomerTraceRecord record = new CustomerTraceRecord();
        record.setUserId(1001);         // FIXME 测试用
        record.setCustomerId(custId);
        record.setTimes(nowTime);
        record.setContent(content);
        customerService.addCustomerTraceItem(record);
        //  TODO    刷新最后跟进时间
        System.err.println("添加的客户跟进记录为："+ record);


///////////////////////////////////////////////////////////////////////////////////////////
        consultantId =1001+""; //FIXME  测试用
        if(StringUtils.isNotBlank(consultantId)){
            // 1.查询出属于该咨询师的客户列表  CustomerInfo param,Page page
            CustomerInfo cInfo = new CustomerInfo();
            // * consultantId在DB里面表现为userId
            cInfo.setUserId(Integer.parseInt(consultantId));
            // * 去掉已经成交的客户！TODO 业务待确认！！
//          cInfo.setCustomerState(CustomerStateEnum.SUCCESS.getStateCode());
            // * 加载初始页面10条
            PageRequest pageRequest = new PageRequest(0,10);
            Map<String,Object> resultMap = customerService.getCustomerInfoListByConsultantId(cInfo,pageRequest);
            log.info("咨询师Id="+consultantId+",客户列表为:customerInfoList="+resultMap);
            // * 推送数据给页面
            modelMap.put("customerPageMap",resultMap);
        }

        return "consultant";        // FIXME 后续改为异步刷新
    }

    /**
     * 获取某客户的跟进记录  [ FIXME 分页先不做]
     *  pageSize=5  已固定
     *  根据时间 Order by   后进先出！
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCustomerTraceRecordById",method = RequestMethod.POST)
    public  Object getCustomerTraceRecordById(@RequestParam(value = "customerId",required = false) Integer customerId,String pageNo) {

        System.err.println("获取某客户的跟进记录: customerId="+customerId+",页数pageNo="+pageNo);
        customerId = customerId==null ? 12:customerId;//FIXME   测试用！！

        log.info("获取访客跟进记录参数为customerId="+customerId+"pageNo="+pageNo);
        //页数控制
        pageNo = StringUtils.isBlank(pageNo)?"1":"pageNo";
        Integer pageNum = Integer.parseInt(pageNo);
        pageNum = pageNum <1 ? 1 : pageNum;
        // TODO pageNum = pageNum > totalPage totalPage : pageNum;
        CustomerTraceRecord recordParam = new CustomerTraceRecord();
        recordParam.setCustomerId(customerId);
        //程序中页数=实际页数-1
        PageRequest pageParam = new PageRequest(pageNum-1,5);
        List<CustomerTraceRecord> result = customerService.getCustomerTraceRecordById(recordParam, pageParam);
        log.info("@result="+result);
        System.err.println("获取访客customerId="+ customerId+"的跟进记录:"+result);
        return result;

    }





}
