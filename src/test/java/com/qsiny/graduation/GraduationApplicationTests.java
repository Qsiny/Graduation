package com.qsiny.graduation;

import com.qsiny.graduation.Mapper.UserMapper;
import com.qsiny.graduation.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

@Slf4j
@SpringBootTest
class GraduationApplicationTests {

    @Resource
    UserMapper userMapper;

    @Resource
    JdbcTemplate jdbcTemplate;

    @Resource
    DataSource dataSource;

    @Test
    void contextLoads() {
        User user = userMapper.login("qin123456", "123123123");
        System.out.println(user);

    }

    @Test
    void test(){

    }

    @Test
    public void test2(){

        User lisi = new User("lisi", "23222", "23232651");
        userMapper.addUser(lisi);


        List<User> users = userMapper.selectUsers();
        for (User user : users) {
            System.out.println(user);
        }

    }

}
