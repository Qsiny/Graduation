package com.qsiny.graduation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Qin
 */
@SpringBootApplication
@MapperScan("com.qsiny.graduation.Mapper")
public class GraduationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(GraduationApplication.class, args);
        String[] names = run.getBeanDefinitionNames();
//        for (String name : names) {
//            System.out.println(name);
//        }

    }

}
