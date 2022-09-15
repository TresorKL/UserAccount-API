package com.example.useraccountapp.controller;

import com.example.useraccountapp.event.RegistrationCompleteEvent;
import com.example.useraccountapp.model.UserModel;
import com.example.useraccountapp.service.UserServiceInterface;
import com.example.useraccountapp.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserAccountController {

    @Autowired
    public UserServiceInterface userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String createUserAccount(@RequestBody UserModel userModel, HttpServletRequest request){

        User user= userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(user, userService.createAppUrl(request)));

        return  "success";
    }

    @GetMapping("/verifyRegistrationToken")
    public String verifyRegistrationToken(@RequestParam("token") String token){

        String result ="";

        result =userService.validateToken(token);


        return result;
    }
}
