package com.hzit.crm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;


/**
 * Created by Administrator on 2016/6/26.
 */
@Configuration
@EnableAutoConfiguration
@MapperScan("com.hzit.crm.core.mapper")
@ComponentScan({"com.hzit.crm","com.fc"})
//@ImportResource(value = "classpath:dubbo.xml")
public class ApplicationWeb extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ApplicationWeb.class);
    }




}
