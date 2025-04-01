package com.HanglyGroup.HanglyBackend.controllers;

import com.HanglyGroup.HanglyBackend.dto.UserCreateDTO;
import com.HanglyGroup.HanglyBackend.projections.UserMinProjection;
import com.HanglyGroup.HanglyBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/profile")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getProfile(){
        UserMinProjection user = userService.getProfile();
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<String> editUser(@RequestBody UserCreateDTO userCreateDTO){
        UserMinProjection userMinProjection = userService.getProfile();
        userService.editUser(userCreateDTO, userMinProjection.getUserId());
        return ResponseEntity.ok("User edited.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(){
        UserMinProjection user = userService.getProfile();
        userService.deleteUser(user.getUserId());
        return ResponseEntity.ok("User deleted");
    }
}
