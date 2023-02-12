package com.psjw.kakaoshareprototype.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * packageName : com.psjw.kakaoshareprototype.controller
 * fileName : IndexController
 * author : psjw
 */
@Controller
@Slf4j
public class IndexController {


    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/user")
    public @ResponseBody String user(){
        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager(){
        return "manager";
    }



    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard/dashboard";
    }

    @GetMapping("/joinProc")
    public @ResponseBody String joinProc(){
        return "회원가입 완료됨";
    }
}
