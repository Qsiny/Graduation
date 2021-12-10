package com.qsiny.graduation.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Qin
 * @description: 用来注册bean到容器中
 * @date 2021/12/5 12:35
 */
@Configuration
public class MyResourceConfig {


    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource(){
       return new DruidDataSource();
    }

}
