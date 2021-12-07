package com.qsiny.graduation.service;

import com.qsiny.graduation.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qin
 */

public interface UserService {
    int addUser(User user);

    int deleteUserById(int id);

    int updateUser(User user);

    User selectUserById(int id);

    List<User> selectUsers();

    User login(String usernameOrTel,String password);
}
