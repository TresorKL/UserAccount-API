package com.example.useraccountapp.service;


import com.example.useraccountapp.model.UserModel;
import com.example.useraccountapp.repository.UserRepository;
import com.example.useraccountapp.repository.VerificationTokenRepository;
import com.example.useraccountapp.user.User;
import com.example.useraccountapp.verificationtoken.VerificationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Slf4j
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

    @Override
    public String validateToken(String token) {

        VerificationToken verificationToken= verificationTokenRepository.findByToken(token);

        if(verificationToken==null){
            return "invalid";
        }

        User user = verificationToken.getUser();
        Calendar calendar= Calendar.getInstance();

        if(verificationToken.getExpirationDate().getTime() - calendar.getTime().getTime()<=0){
            verificationTokenRepository.delete(verificationToken);
            return "Expired";
        }

        user.setAccountEnable(true);
        userRepository.save(user);

        return "Valid";
    }

    @Override
    public String  resendVerificationToken(String oldToken, HttpServletRequest request) {

        VerificationToken verificationToken = verificationTokenRepository.findByToken(oldToken);

        if(verificationToken==null){
            return "invalid";
        }

        String newToken = UUID.randomUUID().toString();
        verificationToken.setToken(newToken);
        verificationToken.setExpirationDate(determineExpirationTime(10));
        verificationTokenRepository.save(verificationToken);

        String url = createAppUrl(request)+"verifyRegistrationToken?token="+newToken;
        log.info("new verification URL : "+url);
        return "new token sent";
    }

    private Date determineExpirationTime(int expirationTime) {

        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE,expirationTime);
        return new Date(calendar.getTime().getTime());
    }
}
