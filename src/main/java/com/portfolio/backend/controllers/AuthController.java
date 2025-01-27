package com.portfolio.backend.controllers;

import com.portfolio.backend.config.JwtTokenUtil;
import com.portfolio.backend.services.UserService;
import com.portfolio.backend.dto.RegisterRequest;
import com.portfolio.backend.dto.LoginRequest;
import com.portfolio.backend.dto.AuthResponse;
import com.portfolio.backend.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = userService.registerUser(request.getUsername(), request.getEmail(), request.getPassword());
        String token = jwtTokenUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(new AuthResponse(
            token,
            "Bearer",
            user.getUsername(),
            user.getEmail(),
            user.getRole().toString()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userService.loginUser(request.getEmail(), request.getPassword());
        String token = jwtTokenUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(new AuthResponse(
            token,
            "Bearer",
            user.getUsername(),
            user.getEmail(),
            user.getRole().toString()
        ));
    }}