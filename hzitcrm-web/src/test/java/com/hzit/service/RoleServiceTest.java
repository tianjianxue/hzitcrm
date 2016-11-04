package com.hzit.service;

import com.hzit.crm.ApplicationWeb;
import com.hzit.crm.core.entity.RoleModule;
import com.hzit.crm.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by 冼耀基 on 2016/10/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationWeb.class)
public class RoleServiceTest {
    @Autowired
    private RoleService roleService;
    @Test
    public void test(){
        List<RoleModule> roleModuleList = roleService.findModuleByUserId("1006");
        if(roleModuleList != null && roleModuleList.size() > 0){
            //System.out.println(roleModuleList.);
        }
    }
}
