package com.example.useraccountapp.controller;

import com.example.useraccountapp.model.UserModel;
import com.example.useraccountapp.service.UserServiceInterface;
import com.example.useraccountapp.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAccountController {

    @Autowired
    public UserServiceInterface userServiceInterface;

    @PostMapping("/register")
    public User createUserAccount(@RequestBody UserModel userModel){


        return  userServiceInterface.registerUser(userModel);
    }
}
