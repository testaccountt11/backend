package com.portfolio.backend.controllers;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.portfolio.backend.models.CompetitionRegistration;
import com.portfolio.backend.services.CompetitionService;
@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {
    private final CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerForCompetition(@RequestBody CompetitionRegistration registration) {
        try {
            registration.setRegisteredAt(LocalDateTime.now());
            CompetitionRegistration saved = competitionService.register(registration);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}