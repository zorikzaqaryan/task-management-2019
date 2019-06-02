package com.example.task.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    
    @GetMapping("/")
    public String index() {
        return "login";
    }
    
    @GetMapping("/error")
    public String error() {
        return "login";
    }
    
    @GetMapping("/login")
    public String home() {
        return "login";
    }
    
}