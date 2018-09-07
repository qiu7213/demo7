package com.example.demo.controller;


import com.example.demo.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {



    @RequestMapping("/hello")
    public String hello(){
        return "Hello Sping Boot";
    }
}
