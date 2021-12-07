package com.qsiny.graduation.service.Impl;

import com.qsiny.graduation.Mapper.UserMapper;
import com.qsiny.graduation.pojo.User;
import com.qsiny.graduation.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Qin
 * @description: TODO
 * @date 2021/12/5 12:24
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int deleteUserById(int id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public User selectUserById(int id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public List<User> selectUsers() {
        return userMapper.selectUsers();
    }

    @Override
    public User login(String usernameOrTel, String password) {
        return userMapper.login(usernameOrTel,password);
    }
}
