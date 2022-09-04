package com.baoshi.programmer.config.admin;

import com.baoshi.programmer.interceptor.admin.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class SpringMvcSupport extends WebMvcConfigurationSupport {
    @Autowired
    private LoginInterceptor loginInterceptor;
    protected void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/admin/**","/system/*")
                .excludePathPatterns("/system/login","/system/get_cpacha","/system/addlist","/system/add","/system/upload_photo");
    }
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

    }


}
