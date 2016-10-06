package com.test;

import com.hzit.crm.ApplicationWeb;
import com.hzit.crm.core.entity.Module;
import com.hzit.crm.service.ModuleService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2016/9/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(value = ApplicationWeb.class)
public class TestModeulServices {
    private Logger log = LoggerFactory.getLogger(TestModeulServices.class);
    @Autowired
    ModuleService moduleService;

    @Before
    public void start(){
        log.info("\n");
        log.info("===============================================");
        log.info("===============================================");
    }

    @After
    public void After(){
        log.info("===============================================");
        log.info("===============================================");
    }

    @Test
    public void testone(){

        List<Module> mli= moduleService.searchModuleByWeCharNo("516547");
        for(Module m : mli){
            System.out.println(m.getName());
        }
    }

}
