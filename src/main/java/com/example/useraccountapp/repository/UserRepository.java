package com.example.useraccountapp.repository;

import com.example.useraccountapp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
