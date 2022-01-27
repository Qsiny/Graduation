package com.qsiny.graduation.controller;

import com.qsiny.graduation.config.SecurityConfig;
import com.qsiny.graduation.pojo.User;
import com.qsiny.graduation.service.UserService;
import com.qsiny.graduation.util.WebUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author Qin
 * @description: TODO
 * @date 2021/12/5 13:31
 */

@Controller
public class UserController {

    @Resource
    RedisTemplate<String,Object> redisTemplate;

    @Resource
    Filter springSecurityFilterChain;

    @Resource
    private UserService userServiceImpl;

    @Resource
    private UserDetailsService userDetailsServiceImpl;

    @RequestMapping(value = {"/","/index"})
    public String index(){
        return "index";
    }


    @GetMapping("/toRegis")
    public String toRegis(){
        return "regis";
    }

    //    发送验证码,并将电话号码和验证码存储在redis中
    @GetMapping("/#vCode")
    public void code(){


    }




//    @PostMapping("/login")
//    public String login(String usernameOrTel, String password, HttpSession httpSession, Model model){
//        User user = userServiceImpl.login(usernameOrTel, password);
//
//        if(user == null){
//            model.addAttribute("errorMsg","用户名或密码错误");
//            model.addAttribute("usernameOrTel",usernameOrTel);
//            //不存在用户，则返回错误信息
//            return "login";
//        }else{
//            //session中存储着用户信息
//            httpSession.setAttribute("userMsg",user);
////            重定向到某一个请求
//            return "redirect:/index.html";
//        }
//    }

    @RequestMapping("/index.html")
    public String toIndex(){
        return "index";
    }

    @GetMapping("/temp")
    public String temp1(){
       return "temp";
    }

    @GetMapping("/user/temp")
    @ResponseBody
    public String temp(){

        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return "user可以访问";
    }

    @GetMapping("/student/temp")
    @ResponseBody
    public String temp2(){
        return "这是student页面";
    }

    @RequestMapping("/loginSuccess")
    public String loginSuccess(){
//        System.out.println("成功进入！！");
        return "login_success";
    }


    @GetMapping("/login.html")
    public String login(){
        return "login";
    }

    @PostMapping("/regis")
    public String regis(HttpServletRequest request){


        User user = WebUtils.populateObject(request, new User());
        System.out.println(user);
        int i = userServiceImpl.addUser(user);
        System.out.println("是否成功添加进数据库:"+i);
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(user.getName());
        System.out.println(userDetails);


        System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());

        return "index";
    }

    @ResponseBody
    @PostMapping("/regis/checkUsernameExist")
    public String checkUsernameExist(String username){
        User user = userServiceImpl.checkUsernameExist(username);
//        如果用户名已经存在了 则反馈给前台
        if(user != null){

            return "用户名已存在";
        }

        return "用户名不存在";
    }

    @ResponseBody
    @PostMapping("/regis/checkTelExist")
    public String checkTelExist(String tel){
        System.out.println(tel);
        User user = userServiceImpl.checkTelExist(tel);
        System.out.println(user);
        if(user != null){
            System.out.println("电话号码已存在");
            return "电话号码已存在";
        }
        System.out.println("电话号码不存在");
        return "电话号码不存在";
    }

    @ResponseBody
    @PostMapping("/regis/test")
    public String test(User user){
        System.out.println(user);
        return "yes";
    }
}
