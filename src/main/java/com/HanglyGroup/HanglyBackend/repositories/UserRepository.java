package com.HanglyGroup.HanglyBackend.repositories;

import com.HanglyGroup.HanglyBackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);
}
