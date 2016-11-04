package com.hzit.crm.web;

import com.hzit.crm.core.entity.Classinfo;
import com.hzit.crm.core.entity.Studentinfo;
import com.hzit.crm.core.entity.UserInfo;
import com.hzit.crm.service.ClassInfoService;
import com.hzit.crm.service.StudentInfoService;
import com.hzit.crm.vo.EasyuiMessager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学员进班
 * Created by 冼耀基 on 2016/10/30.
 */
@Controller
public class StudentInfoController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(StudentInfoController.class);
    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private ClassInfoService classInfoService;

    @RequestMapping("/studentInfo/studentInfo")
    protected String studentInfo(Model map,String customerId){
        Map<String,String>  paramMap = new HashMap<String, String>();
        paramMap.put("classZt","在读");
        List<Classinfo> classinfoList  = classInfoService.searchClassinfoByParams(paramMap);
        map.addAttribute("classinfoList",classinfoList);
        map.addAttribute("customerId",customerId);
        //获取班级信息
        return "/studentInfo/studentInfo";
    }

    /**
     * 学员进班
     * @param studentinfo
     * @param customerId
     * @return
     */
    @RequestMapping("/studentInfo/studentInfoData")
    @ResponseBody
    protected EasyuiMessager studentInfoData(Studentinfo studentinfo, String customerId, HttpServletRequest request){
        HttpSession httpSession = request.getSession(false);
        UserInfo userInfo =(UserInfo)httpSession.getAttribute("userinfo");
        EasyuiMessager easyuiMessager = new EasyuiMessager();
        try{
            studentInfoService.insertStudentinfo(studentinfo,customerId);
            logger.info(userInfo.getRealName()+"有一个学员进班了"+studentinfo.getStudentName());
            easyuiMessager.setMsg("操作成功!");
            easyuiMessager.setSuccess(true);
        }catch(Exception e){
            logger.error(e.getMessage());
            easyuiMessager.setMsg("操作失败!");
            easyuiMessager.setSuccess(false);
        }
        return easyuiMessager;
    }
}
