package com.hzit.crm.service.impl;

import com.hzit.crm.core.entity.CustomerInfo;
import com.hzit.crm.core.entity.Studentinfo;
import com.hzit.crm.core.entity.UserInfo;
import com.hzit.crm.core.mapper.CustomerInfoMapper;
import com.hzit.crm.core.mapper.StudentinfoMapper;
import com.hzit.crm.core.mapper.UserInfoMapper;
import com.hzit.crm.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 冼耀基 on 2016/10/30.
 */
@Service
@Transactional
public class StudentInfoServiceImpl implements StudentInfoService {

    @Autowired
    private StudentinfoMapper studentinfoMapper;

    @Autowired
    private CustomerInfoMapper customerInfoMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 学员进班
     * @param studentinfo
     * @param customerId
     * @throws ParseException
     */
    @Override
    public void insertStudentinfo(Studentinfo studentinfo,String customerId) throws ParseException {
        Map<String,String> paramMap = new HashMap<String, String>();
        paramMap.put("customerId",customerId);
        List<CustomerInfo> customerInfoList = customerInfoMapper.searchCustomerInfoByParams(paramMap);
        CustomerInfo customerInfo = null;
        Map<String,String> userInfoMap = null;

        if(customerInfoList != null && customerInfoList.size() >0){
            userInfoMap = new HashMap<String, String>();
            customerInfo = customerInfoList.get(0);
            studentinfo.setStudentName(customerInfo.getRealName());//姓名
            studentinfo.setSttudentSchool(customerInfo.getGraduateFrom());//毕业学校
            studentinfo.setStudentAge(customerInfo.getAge());//年龄
            //studentinfo.setStudentdes(customerInfo.getMemo());//备注
            studentinfo.setStudentHome(customerInfo.getNativePlace());//家庭住址
            if(customerInfo.getSex() ==1){
                studentinfo.setStudentSex("男");
            }else if(customerInfo.getSex() == 2){
                studentinfo.setStudentSex("女");
            }else{
                studentinfo.setStudentSex("未知");
            }
            userInfoMap.put("userId",customerInfo.getUserId()+"");
            List<UserInfo> userInfoList = userInfoMapper.searchUserInfoByParams(userInfoMap);
            if(userInfoList != null && userInfoList.size() > 0){
                studentinfo.setZixunshiName(userInfoList.get(0).getName());//所属咨询师
            }else{
                studentinfo.setZixunshiName("未知");//所属咨询师
            }
            //studentinfo.setStudentintime(null);//进班时间 ?????
            studentinfo.setStudentTel(customerInfo.getTel());//电话号码
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            studentinfo.setStudentTime(simpleDateFormat.parse(customerInfo.getGraduateTime()));//毕业时间

            if(customerInfo.getEducationBg() == 1){
                studentinfo.setStudentXl("小学");//学历
            }else if(customerInfo.getEducationBg() == 2){
                studentinfo.setStudentXl("初中");//学历
            }
            else if(customerInfo.getEducationBg() == 3){
                studentinfo.setStudentXl("高中");//学历
            }else if(customerInfo.getEducationBg() == 4){
                studentinfo.setStudentXl("中专");//学历
            }else if(customerInfo.getEducationBg() ==5){
                studentinfo.setStudentXl("大专");//学历
            }else if(customerInfo.getEducationBg() ==6){
                studentinfo.setStudentXl("本科");//学历
            }else if(customerInfo.getEducationBg() == 7){
                studentinfo.setStudentXl("研究生");
            }else{
                studentinfo.setStudentXl("其他");
            }
            studentinfo.setStudentYx(customerInfo.getMajorIn());//专业
        }
       studentinfoMapper.insertStudentinfo(studentinfo);

        //把该学员的状态改为已经就读
        CustomerInfo customerInfo1 = new CustomerInfo();
        customerInfo1.setCustomerId(Integer.parseInt(customerId));
        customerInfo1.setCustomerState(6);//该学员进班
        customerInfoMapper.updateCustomerInfo(customerInfo1);
    }
}
