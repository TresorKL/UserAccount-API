package com.example.useraccountapp.event;

import com.example.useraccountapp.user.User;
import org.springframework.context.ApplicationEvent;

public class RegistrationCompleteEvent extends ApplicationEvent {

    public User user;
    public String appUrl;

    public RegistrationCompleteEvent(User user,String appUrl) {
        super(user);
        this.user =user;
        this.appUrl=appUrl;
    }
}
