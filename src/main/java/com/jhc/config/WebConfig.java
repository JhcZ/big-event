package com.jhc.config;

import com.jhc.interceptors.NotLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: 拦截器注册
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2024-01-05 10:59
 **/

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private NotLoginInterceptor notLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //如果该访问对象要登录或注册，则不拦截
        registry.addInterceptor(notLoginInterceptor).excludePathPatterns("/user/login","/user/register");
    }
}