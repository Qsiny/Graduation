package com.qsiny.graduation.controller;

import com.alibaba.fastjson.JSON;
import com.qsiny.graduation.pojo.ResponseResult;
import com.qsiny.graduation.pojo.User;
import com.qsiny.graduation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author Qin
 * @description: TODO
 * @date 2021/12/5 13:31
 */
@Slf4j
@Controller
public class UserController {


    @Resource
    private UserService userService;

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

    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
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

    @ResponseBody
    @PostMapping("/regis")
    public ResponseResult regis(@RequestBody User user){
        //TODO 增加一个验证码校验的功能！！


        //增加一个场景，如果两个用户都判定了一个用户名，但是这个用户名或者电话号码被另外一个注册了后，别人就应该无法注册这个了
        User userByUsername = userService.checkUsernameExist(user.getUserName());
        if(userByUsername != null){
            throw new RuntimeException("该用户名:"+user.getUserName() +"以被注册，请检查！");
        }
        User userByTel = userService.checkTelExist(user.getPhonenumber());
        if(userByTel != null){
            throw new RuntimeException("该电话号码:"+user.getPhonenumber()+"以被注册，请检查！");
        }
        log.info("当前用户{},请求注册",user);
        ResponseResult responseResult = userService.addUser(user);
        log.info("用户:{},注册成功",user.getUserName());
        return responseResult;
    }

    @ResponseBody
    @PostMapping("/regis/checkUsernameExist")
    public String checkUsernameExist(String username){
        User user = userService.checkUsernameExist(username);
//        如果用户名已经存在了 则反馈给前台
        if(user != null){
            log.info("当前用户名存在:"+username);
            return "用户名已存在";
        }

        return "用户名不存在";
    }

    @ResponseBody
    @PostMapping("/regis/checkTelExist")
    public String checkTelExist(String tel){
        User user = userService.checkTelExist(tel);
        if(user != null){
            log.info("电话号码已存在:"+tel);
            return "电话号码已存在";
        }
        return "电话号码不存在";
    }

    @ResponseBody
    @PostMapping("/regis/test")
    public String test(@RequestBody User user){
        System.out.println(user);
//        JSON.parse(user);
//        User user1 = JSON.parseObject(user, User.class);
//        System.out.println("user1:"+user1);
        return "yes";
    }
}
