package com.HanglyGroup.HanglyBackend.services;

import com.HanglyGroup.HanglyBackend.dto.EventEditDTO;
import com.HanglyGroup.HanglyBackend.dto.UserCreateDTO;
import com.HanglyGroup.HanglyBackend.dto.UserEditDTO;
import com.HanglyGroup.HanglyBackend.entities.Address;
import com.HanglyGroup.HanglyBackend.entities.Event;
import com.HanglyGroup.HanglyBackend.entities.User;
import com.HanglyGroup.HanglyBackend.exceptions.EventNotFoundException;
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
        System.out.println("No service: " + userDetails.getUsername());
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
    public void editUser(UserEditDTO userEdit,Long userId){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        User oldUser = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        User newUser = new User(userEdit);
        modelMapper.map(newUser, oldUser);
        userRepository.saveAndFlush(oldUser);
    }

    @Transactional
    public boolean rateUser(Long userLogged, Long userId, double valueRate){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if(user.getUserId().equals(userLogged)){
            return false;
        }
        user.setRate(valueRate);
        userRepository.save(user);
        return true;
    }

    @Transactional
    public void deleteUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }
}
