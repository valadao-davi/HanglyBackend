package com.HanglyGroup.HanglyBackend.repositories;

import com.HanglyGroup.HanglyBackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
