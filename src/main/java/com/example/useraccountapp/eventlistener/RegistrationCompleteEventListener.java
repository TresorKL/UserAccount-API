package com.example.useraccountapp.eventlistener;

import com.example.useraccountapp.event.RegistrationCompleteEvent;
import com.example.useraccountapp.service.UserService;
import com.example.useraccountapp.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        User user=event.getUser();
        String token = UUID.randomUUID().toString();

        userService.saveVerificationToken( user,   token );

        String url =event.getAppUrl()+"verifyRegistrationToken?token="+token;

        String resend =event.getAppUrl()+"resendVerifyRegistrationToken?oldToken="+token;
        //send email
        log.info("click the link to activate your account: "+ url+"\n\n"+"resend new token : "+resend);
    }
}
