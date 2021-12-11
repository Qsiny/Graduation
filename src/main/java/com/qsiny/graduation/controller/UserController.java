package com.qsiny.graduation.controller;

import com.qsiny.graduation.config.SecurityConfig;
import com.qsiny.graduation.pojo.User;
import com.qsiny.graduation.service.UserService;
import com.qsiny.graduation.util.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Qin
 * @description: TODO
 * @date 2021/12/5 13:31
 */
@Controller
public class UserController {

    @Resource
    private UserService userServiceImpl;

    @Resource
    private UserDetailsService userDetailsServiceImpl;

    @GetMapping(value = {"/","/index"})
    public String index(){
        return "index";
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
        System.out.println("所有用户都可以访问"+SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return "所有人都可以访问";
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

        return "index";
    }
}
