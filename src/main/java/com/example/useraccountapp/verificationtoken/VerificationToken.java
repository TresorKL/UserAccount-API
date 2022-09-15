package com.example.useraccountapp.verificationtoken;



import com.example.useraccountapp.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VerificationToken {

   private final int EXPIRATION_TIME=10;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date expirationDate;
    private String token;

    @OneToOne
    @JoinTable(name="jnd_token_user")
    private User user;


    public VerificationToken(User user, String token){
        super();
        this.token=token;
        this.user=user;
        this.expirationDate=determineExpirationTime(EXPIRATION_TIME);
    }

    public VerificationToken( String token) {
        this.token=token;
        this.expirationDate=determineExpirationTime(EXPIRATION_TIME);
    }

    private Date determineExpirationTime( int expirationTime) {

        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE,expirationTime);
        return new Date(calendar.getTime().getTime());
    }
}
