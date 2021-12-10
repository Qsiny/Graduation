package com.qsiny.graduation.Mapper;

import com.qsiny.graduation.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Qin
 */
@Mapper
public interface UserMapper {
    int addUser(@Param("user") User user);

    int deleteUserById(int id);

    int updateUser(@Param("user") User user);

    User selectUserById(int id);

    List<User> selectUsers();

    User login(@Param("usernameOrTel") String usernameOrTel,@Param("password") String password);


    User findUserByUsername(String username);
}
