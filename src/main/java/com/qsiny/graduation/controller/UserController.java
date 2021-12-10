package com.qsiny.graduation.controller;

import com.qsiny.graduation.config.SecurityConfig;
import com.qsiny.graduation.pojo.User;
import com.qsiny.graduation.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author Qin
 * @description: TODO
 * @date 2021/12/5 13:31
 */
@Controller
public class UserController {

    @Resource
    private UserService userServiceImpl;

    @GetMapping(value = {"/","/index"})
    public String index(){
        return "redirect:/toLogin";
    }


    @GetMapping("/toRegis")
    public String toRegis(){
        return "regis";
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

    @GetMapping("/index.html")
    public String toIndex(){
        return "index";
    }

    @GetMapping("/temp")
    @ResponseBody
    public String temp1(){

        return "所有人都可以访问";
    }

    @GetMapping("/user/temp")
    @ResponseBody
    public String temp(){
        return "user可以访问";
    }

    @GetMapping("/student/temp")
    @ResponseBody
    public String temp2(){
        return "这是student页面";
    }


    @GetMapping("/login.html")
    public String login(){
        return "login";
    }
}
