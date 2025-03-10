package com.muskan.codes.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String Hello() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/shoes")
    public String shoes() {
        return "shoes";
    }

    @GetMapping("/explore")
    public String showExplorePage() {
        return "explore";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
}
