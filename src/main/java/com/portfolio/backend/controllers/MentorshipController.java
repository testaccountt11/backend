package com.portfolio.backend.controllers;

import com.portfolio.backend.models.Mentorship;
import com.portfolio.backend.models.User;
import com.portfolio.backend.dto.SessionRequestDTO;
import com.portfolio.backend.models.MentorProfile;
import com.portfolio.backend.services.MentorshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mentorship")
public class MentorshipController {
    private final MentorshipService mentorshipService;

    public MentorshipController(MentorshipService mentorshipService) {
        this.mentorshipService = mentorshipService;
    }

    @GetMapping("/mentors")
    public ResponseEntity<List<MentorProfile>> getAllMentors() {
        return ResponseEntity.ok(mentorshipService.getAllMentors());
    }

    @GetMapping("/mentor/{userId}")
    public ResponseEntity<MentorProfile> getMentorProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(mentorshipService.getMentorProfile(userId));
    }

    @PostMapping("/sessions")
    public ResponseEntity<Mentorship> scheduleSession(@RequestBody Mentorship session) {
        return ResponseEntity.ok(mentorshipService.scheduleSession(session));
    }
    @PostMapping("/sessions/schedule")
    public ResponseEntity<Mentorship> scheduleSession(@RequestBody SessionRequestDTO request, @AuthenticationPrincipal UserDetails userDetails) {
        Long menteeId = ((User) userDetails).getId();
        Mentorship session = mentorshipService.scheduleSession(request, menteeId);
        return ResponseEntity.ok(session);
    }

    @PatchMapping("/sessions/{sessionId}/status")
    public ResponseEntity<Mentorship> updateSessionStatus(
        @PathVariable Long sessionId,
        @RequestParam String status,
        @AuthenticationPrincipal UserDetails userDetails
    ) {
        Mentorship session = mentorshipService.updateSessionStatus(sessionId, status);
        return ResponseEntity.ok(session);
    }
        @GetMapping("/mentor/sessions/{mentorId}")
        public ResponseEntity<List<Mentorship>> getMentorSessions(@PathVariable Long mentorId) {
            return ResponseEntity.ok(mentorshipService.getMentorSessions(mentorId));
        }

        @GetMapping("/mentee/sessions/{menteeId}")
        public ResponseEntity<List<Mentorship>> getMenteeSessions(@PathVariable Long menteeId) {
            return ResponseEntity.ok(mentorshipService.getMenteeSessions(menteeId));
        }
}