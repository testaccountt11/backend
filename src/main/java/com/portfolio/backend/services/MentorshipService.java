package com.portfolio.backend.services;

import com.portfolio.backend.models.Mentorship;
import com.portfolio.backend.models.User;
import com.portfolio.backend.dto.SessionRequestDTO;
import com.portfolio.backend.models.MentorProfile;
import com.portfolio.backend.repositories.MentorshipRepository;
import com.portfolio.backend.repositories.UserRepository;
import com.portfolio.backend.repositories.MentorProfileRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MentorshipService {
    private final MentorshipRepository mentorshipRepository;
    private final UserRepository userRepository;
    private final MentorProfileRepository mentorProfileRepository;

    public MentorshipService(MentorshipRepository mentorshipRepository, 
                           MentorProfileRepository mentorProfileRepository, UserRepository userRepository) {
        this.mentorshipRepository = mentorshipRepository;
        this.mentorProfileRepository = mentorProfileRepository;
        this.userRepository = userRepository;
    }

    public List<MentorProfile> getAllMentors() {
        return mentorProfileRepository.findAll();
    }

    public MentorProfile getMentorProfile(Long userId) {
        return mentorProfileRepository.findByUserId(userId);
    }

    public Mentorship scheduleSession(Mentorship session) {
        return mentorshipRepository.save(session);
    }

    public List<Mentorship> getMentorSessions(Long mentorId) {
        return mentorshipRepository.findByMentorId(mentorId);
    }

    public List<Mentorship> getMenteeSessions(Long menteeId) {
        return mentorshipRepository.findByMenteeId(menteeId);
    }
    public Mentorship scheduleSession(SessionRequestDTO request, Long menteeId) {
    User mentee = userRepository.findById(menteeId)
        .orElseThrow(() -> new RuntimeException("Mentee not found"));
    
    User mentor = userRepository.findById(request.getMentorId())
        .orElseThrow(() -> new RuntimeException("Mentor not found"));

    Mentorship session = new Mentorship();
    session.setMentor(mentor);
    session.setMentee(mentee);
    session.setTopic(request.getTopic());
    session.setDescription(request.getMessage());
    session.setScheduledTime(request.getScheduledTime());
    session.setDuration(request.getDuration());
    session.setStatus("PENDING");

    return mentorshipRepository.save(session);
}

public Mentorship updateSessionStatus(Long sessionId, String status) {
    Mentorship session = mentorshipRepository.findById(sessionId)
        .orElseThrow(() -> new RuntimeException("Session not found"));
    
    session.setStatus(status);
    return mentorshipRepository.save(session);
}
}