package com.greenStitch.controllers;

import com.greenStitch.entities.User;
import com.greenStitch.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String hello() {
        System.out.println("Get hello executed");
        return "Hello from GreenStitch";
    }

}
