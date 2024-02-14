package com.pranavbale.security.controller;

import com.pranavbale.security.entity.UserInfo;
import com.pranavbale.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public String createUser(@RequestBody UserInfo userInfo) {
        userService.createUser(userInfo);
        return "User Create Successfully" + userInfo.getUserName();
    }
}
