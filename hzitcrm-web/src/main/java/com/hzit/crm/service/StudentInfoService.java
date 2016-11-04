package com.hzit.crm.service;

import com.hzit.crm.core.entity.Studentinfo;

import java.text.ParseException;

/**
 * Created by 冼耀基 on 2016/10/30.
 */
public interface StudentInfoService {
    void insertStudentinfo(Studentinfo studentinfo,String customerId) throws ParseException;
}
