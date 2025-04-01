package com.HanglyGroup.HanglyBackend.services;

import com.HanglyGroup.HanglyBackend.dto.UserCreateDTO;
import com.HanglyGroup.HanglyBackend.entities.User;
import com.HanglyGroup.HanglyBackend.exceptions.UserNotFoundException;
import com.HanglyGroup.HanglyBackend.exceptions.UserRegisteredException;
import com.HanglyGroup.HanglyBackend.projections.UserMinProjection;
import com.HanglyGroup.HanglyBackend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserMinProjection getProfile(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();
        return userRepository.getUserProfile(email).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public void registerUser(UserCreateDTO userCreateDTO){
        if(userCreateDTO.getName() == null || userCreateDTO.getEmail() == null || userCreateDTO.getPassword() == null){
            throw new RuntimeException("Invalid fields!");
        }
        Optional<UserMinProjection> findProfile = userRepository.getUserProfile(userCreateDTO.getEmail());
        if(findProfile.isPresent()){
            throw new UserRegisteredException();
        }
        userRepository.saveAndFlush(new User(userCreateDTO));
    }

    @Transactional
    public void editUser(UserCreateDTO userEdit, Long userId){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(userEdit, user);
        userRepository.save(user);
    }


    @Transactional
    public void deleteUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }
}
