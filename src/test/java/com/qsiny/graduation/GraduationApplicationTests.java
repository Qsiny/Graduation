package com.qsiny.graduation;


import com.qsiny.graduation.Mapper.UserMapper;
import com.qsiny.graduation.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.*;

@Slf4j
@SpringBootTest
class GraduationApplicationTests {

    @Resource
    UserMapper userMapper;

    @Resource
    JdbcTemplate jdbcTemplate;

    @Resource
    DataSource dataSource;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        User user = userMapper.login("qin123456", "123123");
        System.out.println(user);

    }

    @Test
    void test(){
        String pwd = "123456";
        String encode = passwordEncoder.encode(pwd);
        System.out.println(encode);
        //$2a$10$Iv3v.SZ2r6bZqpRpjGp57uK7NLTR69kM.KByfM5ZOTlbhvYpFB8yC
        //$2a$10$nhUcG2Q/NjShwvajCfBHhu1f7iEI0n3wtYivDzKzJTvTZ9nQA9036
        System.out.println(encode.length());
    }

    @Test
    public void test2(){

        User lisi = new User("lisi", "23222", "23232651");
        userMapper.addUser(lisi);

        String msg = "abcd";
        msg = "dcba";
        System.out.println(msg);

        List<User> users = userMapper.selectUsers();
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void test3(){
        User user = new User("zhangsan", "123123", "1768394145");
        userMapper.addUser(user);

    }

    @Test
    public void test4(){
        System.out.println(passwordEncoder.matches("qin123", "$2a$10$l.hybL1Zg9ZbtSHoWwes7eZABtKcQRP.KJqDbeqA2LPB.aaVvvZTS"));

    }



}
