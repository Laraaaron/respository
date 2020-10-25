package com.forezp.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 拦截注册表
         */
        registry.addInterceptor(new UserLoginInterceptorBySpring()).addPathPatterns("/blog/addarticle");
        super.addInterceptors(registry);
    }
}
