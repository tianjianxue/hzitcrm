package com.hzit.crm;

import com.hzit.crm.interceptors.LoginInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;


/**
 * Created by Administrator on 2016/6/26.
 */
@Configuration
@EnableAutoConfiguration
@MapperScan("com.hzit.crm.core.mapper")
@ComponentScan({"com.hzit.crm","com.fc"})
//@ImportResource(value = "classpath:dubbo.xml")
public class ApplicationWeb extends SpringBootServletInitializer {


//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(ApplicationWeb.class);
//    }



}
