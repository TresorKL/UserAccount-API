package com.example.useraccountapp.repository;

import com.example.useraccountapp.verificationtoken.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
}
