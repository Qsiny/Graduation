package com.qsiny.graduation.controller;

import com.qsiny.graduation.pojo.ResponseResult;
import com.qsiny.graduation.pojo.User;
import com.qsiny.graduation.service.UserService;
import com.qsiny.graduation.utils.JwtUtil;
import com.qsiny.graduation.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Qin
 * @description: TODO
 * @date 2022/3/5 15:03
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisCache redisCache;


    @PostMapping("/user/login")
    public ResponseResult login(@RequestParam("usernameOrTel") String usernameOrTel,@RequestParam("password") String password){
        log.info("接收到了数据：usernameortel:{}",usernameOrTel);
       return userService.login(usernameOrTel,password);
    }

    @PreAuthorize("hasAnyAuthority('user')")
    @RequestMapping("/helloOne")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/user/logout")
    public ResponseResult logout(){
        return userService.logout();
    }

}
