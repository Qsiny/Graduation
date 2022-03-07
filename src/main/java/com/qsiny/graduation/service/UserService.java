package com.qsiny.graduation.service;

import com.qsiny.graduation.pojo.ResponseResult;
import com.qsiny.graduation.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qin
 */

public interface UserService {
    ResponseResult addUser(User user);

    int deleteUserById(int id);

    int updateUser(User user);

    User selectUserById(int id);

    List<User> selectUsers();

    User checkUsernameExist(String username);

    User checkTelExist(String tel);

    ResponseResult login(String message,String password);

    ResponseResult logout();
}
