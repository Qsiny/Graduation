package com.qsiny.graduation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Qsiny on 2022/3/15 11:09
 * @author Qsiny
 */
@Controller
public class TestController {

    @RequestMapping("/testSendMessage")
    public String test1(Model model) {
        model.addAttribute("test1",false);
        return "1";
    }
}
