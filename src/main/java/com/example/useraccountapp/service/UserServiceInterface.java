package com.example.useraccountapp.service;

import com.example.useraccountapp.model.UserModel;
import com.example.useraccountapp.user.User;

import javax.servlet.http.HttpServletRequest;

public interface UserServiceInterface {
    User registerUser(UserModel userModel);
    void saveVerificationToken(User user, String token);

    String createAppUrl(HttpServletRequest request);

    String validateToken(String token);

    String  resendVerificationToken(String oldToken, HttpServletRequest request);
}
