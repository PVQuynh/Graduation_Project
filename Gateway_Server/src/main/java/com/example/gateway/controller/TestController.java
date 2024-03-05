package com.example.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping
    public String a(){
        return "OK";
    }
    @GetMapping("/hust/user/test")
    public String ab(){
        return "OK";
    }
}
