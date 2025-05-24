package com.HanglyGroup.HanglyBackend.repositories;

import com.HanglyGroup.HanglyBackend.entities.User;
import com.HanglyGroup.HanglyBackend.projections.UserMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<UserMinProjection> getUserProfile(String email);


}
