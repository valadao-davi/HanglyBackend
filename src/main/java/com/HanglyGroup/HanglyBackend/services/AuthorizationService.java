package com.HanglyGroup.HanglyBackend.services;

import com.HanglyGroup.HanglyBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Recebendo o email: " + email);
        UserDetails user = userRepository.findByEmail(email);
        System.out.println("User: " + user.getUsername());
        return user;
    }
}
