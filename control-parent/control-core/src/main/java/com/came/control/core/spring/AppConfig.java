package com.came.control.core.spring;

import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableAsync
@EnableWebMvc
@Configuration
@ComponentScan({
	"com.came.control.core",
	"com.came.control.model",
	"com.came.control.beans.funciones"})
@Import({WebService.class, InitialData.class, CfgMysql.class, QuartzConfiguration.class, AsyncCustomizeExcutorCtrlCore.class})
public class AppConfig extends SpringBootServletInitializer/*AbstractSecurityWebApplicationInitializer*/ {

}
