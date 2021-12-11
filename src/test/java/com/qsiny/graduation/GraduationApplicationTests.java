package com.qsiny.graduation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.qsiny.graduation.Mapper.UserMapper;
import com.qsiny.graduation.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.*;

@Slf4j

class GraduationApplicationTests {

    @Resource
    UserMapper userMapper;

    @Resource
    JdbcTemplate jdbcTemplate;

    @Resource
    DataSource dataSource;

    @Resource
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

        //创建一个JWT
        JWTCreator.Builder builder = JWT.create();


        //用来设置时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,7);

        //token的设置
        String token = builder.withExpiresAt(calendar.getTime())
                .withClaim("username", "zhangsan")
                .withClaim("userId", 11)
                .sign(Algorithm.HMAC256("123456789"));

        System.out.println(token);

        //比较 以及或许token中的值
        DecodedJWT decode = JWT.decode(token);
        String username = decode.getClaim("username").asString();
        Integer userId = decode.getClaim("userId").asInt();
        System.out.println("当前用户名"+username+",当前用户Id"+userId);


        HashMap<String, String> map = new HashMap();

        map.put("name1","123");
        map.put("name2","123");
        map.put("name3","123");
        map.put("name4","123");



        Set set1 = map.keySet();
        for (Object o : set1) {
            System.out.println(o.toString());
        }

        for (int i = 0; i < set1.size(); i++) {

        }

        ArrayList arrayList;


        Set set = map.entrySet();
        set.iterator();

        ArrayList list;

    }



}
