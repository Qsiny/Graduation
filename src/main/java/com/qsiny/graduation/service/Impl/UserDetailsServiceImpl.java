package com.qsiny.graduation.service.Impl;

import com.qsiny.graduation.Mapper.UserMapper;
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

    @Resource
    private PasswordEncoder passwordEncoder;

    private final String DEFAULT_ROLE = "user";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserByUsername(username);
        if(user == null){
            log.info("当前用户名不存在{}",username);
            return null;
        }

        String role = user.getRole();
        List<GrantedAuthority> list = new ArrayList<>();
        GrantedAuthority grantedAuthority;
        //添加角色
        if(role == null){
            grantedAuthority = new SimpleGrantedAuthority(DEFAULT_ROLE);
        }else{
            grantedAuthority = new SimpleGrantedAuthority(role);
        }
        list.add(grantedAuthority);
//        org.springframework.security.core.userdetails.User user1 = new org.springframework.security.core.userdetails.User(user.getName(), passwordEncoder.encode(user.getPassword()), list);
//        System.out.println(user1.getPassword());

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), list);
    }
}
