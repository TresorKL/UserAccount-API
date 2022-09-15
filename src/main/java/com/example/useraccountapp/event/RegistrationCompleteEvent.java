package com.example.useraccountapp.event;

import com.example.useraccountapp.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    public User user;
    public String appUrl;

    public RegistrationCompleteEvent(User user,String appUrl) {
        super(user);
        this.user =user;
        this.appUrl=appUrl;
    }
}
