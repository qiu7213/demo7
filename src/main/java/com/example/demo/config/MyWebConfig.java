package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//@Configuration
public class MyWebConfig extends WebMvcConfigurationSupport {
//
//    @Value("${web.upload.path}")
//    private String uploadPath;

//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // 把静态资源映射加载进来
//        //将所有/static/** 访问都映射到classpath:/static/ 目录下
//        //registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/resources/static/");
//
//    }

    /**
     *
        Spring Boot 对静态资源映射提供了默认配置
        Spring Boot 默认将  /**  所有访问映射到以下目录：
        classpath:/static
        classpath:/public
        classpath:/resources
        classpath:/META-INF/resources
        Spring Boot 默认会挨个从 public resources static 里面找是否存在相应的资源，如果有则直接返回。
     */


//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        // 把拦截器加载进来
//        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
//    }



    // WebMvcConfigurationSupport 与WebMvcConfigurerAdapter 都可以配置MVC,
    // WebMvcConfigurationSupport 支持的自定义的配置更多更全，
    // WebMvcConfigurerAdapter有的WebMvcConfigurationSupport 都有.
    //WebMvcConfigurerAdapter 已过时

}
