package com.example.LittleAvito.controllers;

import com.example.LittleAvito.Models.User;
import com.example.LittleAvito.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

//    @GetMapping("/registration")
    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже есть");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/hello")
    public String securityUrl(){
        return "hello";
    }



}