package com.qsiny.graduation.service.Impl;

import com.qsiny.graduation.Mapper.MenuMapper;
import com.qsiny.graduation.Mapper.UserMapper;
import com.qsiny.graduation.pojo.LoginUser;
import com.qsiny.graduation.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Qin
 * @description: TODO
 * @date 2021/12/10 17:59
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String message) throws UsernameNotFoundException {

        User user = userMapper.findUserByUsernameOrTel(message);
        if(user == null){
            throw new RuntimeException("用户名或密码错误");
        }

        //从数据库中查询用户权限
        List<String> menus = menuMapper.selectPermsByUserId(user.getId());

        //手动添加user权限
//        ArrayList<String> permissions = new ArrayList<>();
//        permissions.add("user");

        return new LoginUser(user,menus);


    }
}
