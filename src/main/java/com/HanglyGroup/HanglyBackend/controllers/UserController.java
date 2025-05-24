package com.HanglyGroup.HanglyBackend.controllers;

import com.HanglyGroup.HanglyBackend.dto.UserEditDTO;
import com.HanglyGroup.HanglyBackend.entities.User;
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
    public ResponseEntity<String> editUser(@RequestBody UserEditDTO user){
        UserMinProjection userMinProjection = userService.getProfile();
        userService.editUser(user, userMinProjection.getUserId());
        return ResponseEntity.ok("User edited.");
    }

    @PatchMapping(value = "{id}")
    public ResponseEntity<String> rateUser(@RequestBody double rate, @PathVariable Long id) {
        UserMinProjection userMinProjection = userService.getProfile();
         boolean result = userService.rateUser(userMinProjection.getUserId(), id, rate);
         if(result){
             return ResponseEntity.ok("Sucess.");
         }else{
             return ResponseEntity.badRequest().body("User can't rate himself.");
         }

    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(){
        UserMinProjection user = userService.getProfile();
        userService.deleteUser(user.getUserId());
        return ResponseEntity.ok("User deleted");
    }
}
