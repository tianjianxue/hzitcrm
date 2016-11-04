package com.hzit.mapper.test;

import com.hzit.crm.ApplicationWeb;
import com.hzit.crm.core.entity.Studentinfo;
import com.hzit.crm.core.mapper.StudentinfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 冼耀基 on 2016/10/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationWeb.class)
public class StudentInfoMapperTest {
   /* @Autowired
    private StudentinfoMapper studentinfoMapper;
    @Test
    public void test(){
        Studentinfo studentinfo = new Studentinfo();
        studentinfo.setStudentName("admin");
        studentinfo.setStudentStatus("Runtime");
        studentinfoMapper.insertStudentinfo(studentinfo);
    }*/
   @Test
    public void test(){

   }
}
