package com.HanglyGroup.HanglyBackend.controllers;

import com.HanglyGroup.HanglyBackend.dto.LoginResponseDTO;
import com.HanglyGroup.HanglyBackend.dto.UserCreateDTO;
import com.HanglyGroup.HanglyBackend.dto.UserLoginDTO;
import com.HanglyGroup.HanglyBackend.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid UserLoginDTO userLoginDTO){
            String token = authenticationService.login(userLoginDTO);
            return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserCreateDTO userCreateDTO){
        authenticationService.register(userCreateDTO);
        return ResponseEntity.ok("User registered");
    }
}
