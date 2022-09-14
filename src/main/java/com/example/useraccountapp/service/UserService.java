package com.example.useraccountapp.service;


import com.example.useraccountapp.model.UserModel;
import com.example.useraccountapp.repository.UserRepository;
import com.example.useraccountapp.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {

   @Autowired
   public UserRepository userRepository;

   @Autowired
   public  PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserModel userModel) {

        User user =new User();
        user.setName(userModel.getName());
        user.setEmail(userModel.getEmail());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setAccountEnable(false);
       return userRepository.save(user);
    }
}
