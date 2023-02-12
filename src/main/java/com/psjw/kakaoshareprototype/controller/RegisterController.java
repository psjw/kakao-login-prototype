package com.psjw.kakaoshareprototype.controller;

import com.psjw.kakaoshareprototype.dto.UserRequestDto;
import com.psjw.kakaoshareprototype.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * packageName : com.psjw.kakaoshareprototype.controller
 * fileName : RegisterController
 * author : psjw
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class RegisterController {
    private final UserService userService;

    @PostMapping("/register")
    public String register(UserRequestDto userRequestDto, HttpSession httpSession){
        userRequestDto.setRole("ROLE_USER");
        userService.save(userRequestDto);
        return "redirect:/loginForm";

    }

    @GetMapping("/registerForm")
    public String registerFrom(){
        return "login/registerForm";
    }
}
