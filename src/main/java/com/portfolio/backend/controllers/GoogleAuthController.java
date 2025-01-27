package com.portfolio.backend.controllers;

import com.portfolio.backend.config.JwtTokenUtil;
import com.portfolio.backend.services.UserService;
import com.portfolio.backend.dto.GoogleAuthRequest;
import com.portfolio.backend.dto.AuthResponse;
import com.portfolio.backend.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth/google")
public class GoogleAuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    public GoogleAuthController(UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> googleSignIn(@RequestBody GoogleAuthRequest request) {
        User user = userService.handleGoogleSignIn(request.getEmail(), request.getFullName());
        String token = jwtTokenUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(new AuthResponse(
            token,
            "Bearer",
            user.getUsername(),
            user.getEmail(),
            user.getRole().toString()
        ));
    }
}