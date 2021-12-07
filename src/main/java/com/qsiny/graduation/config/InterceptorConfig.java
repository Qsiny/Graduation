package com.qsiny.graduation.config;

import com.qsiny.graduation.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Qin
 * @description: TODO
 * @date 2021/12/5 16:53
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有请求，但是放过静态资源
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/**")
                //放行的请求
                .excludePathPatterns("/","/toLogin","/toRegis","/login","/regis","/css/**","/fonts/**","/images/**","/jquery/**");
    }


}
