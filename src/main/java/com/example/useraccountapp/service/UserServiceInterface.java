package com.example.useraccountapp.service;

import com.example.useraccountapp.model.UserModel;
import com.example.useraccountapp.user.User;

public interface UserServiceInterface {
    User registerUser(UserModel userModel);
}
