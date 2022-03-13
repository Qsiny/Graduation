package com.qsiny.graduation.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qsiny.graduation.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Qin
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


    User login(@Param("usernameOrTel") String usernameOrTel,@Param("password") String password);

    User findUserByUsername(String username);

    User findUserByTel(String tel);

    User findUserByUsernameOrTel(@Param("usernameOrTel") String usernameOrTel);

    int changeDelFlag(@Param("user") User user);

}
