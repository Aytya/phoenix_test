package com.example.phoenix_test.controller;

import com.example.phoenix_test.dto.AuthenticationResponse;
import com.example.phoenix_test.dto.LoginRequest;
import com.example.phoenix_test.dto.RegistrationRequest;
import com.example.phoenix_test.entity.User;
import com.example.phoenix_test.service.AuthenticationService;
import jakarta.security.auth.message.AuthException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationService authService;

    public AuthController(AuthenticationService authService) {
        this.authService = authService;
    }


    @PostMapping({"/register"})
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(this.authService.register(request));
    }

    @PostMapping({"/login"})
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) throws AuthException {
        return ResponseEntity.ok(this.authService.authenticate(request));
    }
}