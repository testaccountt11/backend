package com.portfolio.backend.controllers;

import com.portfolio.backend.dto.InternshipApplicationDTO;
import com.portfolio.backend.models.Internship;
import com.portfolio.backend.models.InternshipApplication;
import com.portfolio.backend.services.InternshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/internships")
public class InternshipController {
    private final InternshipService internshipService;

    public InternshipController(InternshipService internshipService) {
        this.internshipService = internshipService;
    }
    @PostMapping("/apply")
    public ResponseEntity<?> applyForInternship(@ModelAttribute InternshipApplicationDTO application) {
        try {
            InternshipApplication result = internshipService.submitApplication(application);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

   
    @GetMapping
    public ResponseEntity<List<Internship>> getAllInternships(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String location) {
        if (type != null) {
            return ResponseEntity.ok(internshipService.getInternshipsByType(type));
        } else if (location != null) {
            return ResponseEntity.ok(internshipService.getInternshipsByLocation(location));
        }
        return ResponseEntity.ok(internshipService.getAllInternships());
    }
        
    
}