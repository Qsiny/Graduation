package com.qsiny.graduation.Mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qsiny.graduation.enums.UserStatusEnum;
import com.qsiny.graduation.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Autowired
    MenuMapper menuMapper;


    @Test
    void addUser() {
        User user = new User();
        user.setUserName("user123");
        user.setPassword("$2a$10$Z/6MlsRwEb4OYWmc24p25ehe.40EfpmfFH4DhX.vnyw3XoHhUprKO");
        user.setPhonenumber("17683941775");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    void deleteUserById() {
        int i = userMapper.deleteById(3L);
        System.out.println(i);
    }

    @Test
    void updateUser() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        User user = new User();
        user.setId(1L);
        user.setStatus(UserStatusEnum.normal);
        System.out.println(userMapper.updateById(user));
    }

    @Test
    void selectUserById() {
    }

    @Test
    void selectUsers() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email").like("user_name","user");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void login() {
        User login = userMapper.login("17683941774", "$2a$10$Z/6MlsRwEb4OYWmc24p25ehe.40EfpmfFH4DhX.vnyw3XoHhUprKO");
        System.out.println(login);
    }

    @Test
    void findUserByUsername() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("count(*)");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    void findUserByTel() {

        Page<User> page = new Page<>(2,3);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        queryWrapper.select("user_name","password");
        userMapper.selectPage(page,queryWrapper);
        List<User> records = page.getRecords();
        records.forEach(System.out::println);


    }

    @Test
    void findUserByUsernameOrTel() {

        List<String> strings = menuMapper.selectPermsByUserId(4L);
        strings.forEach(System.out::println);
    }
}