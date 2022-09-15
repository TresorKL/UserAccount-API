package com.example.useraccountapp.service;


import com.example.useraccountapp.model.UserModel;
import com.example.useraccountapp.repository.UserRepository;
import com.example.useraccountapp.repository.VerificationTokenRepository;
import com.example.useraccountapp.user.User;
import com.example.useraccountapp.verificationtoken.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService implements UserServiceInterface {

   @Autowired
   public UserRepository userRepository;

   @Autowired
   public  PasswordEncoder passwordEncoder;

   @Autowired
   public VerificationTokenRepository verificationTokenRepository;

    @Override
    public User registerUser(UserModel userModel) {

        User user =new User();
        user.setName(userModel.getName());
        user.setEmail(userModel.getEmail());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setAccountEnable(false);
       return userRepository.save(user);
    }

    public void saveVerificationToken(User user, String token) {

        VerificationToken verificationToken= new VerificationToken(user,token);

        verificationTokenRepository.save(verificationToken);

    }

    @Override
    public String createAppUrl(HttpServletRequest request) {


        return "http://"+
                request.getServerName()+
                ":"+
                request.getServerPort()+"/"+
                request.getContextPath();
    }
}
