package com.portfolio.backend.services;

import com.portfolio.backend.models.CompetitionRegistration;
import com.portfolio.backend.repositories.CompetitionRegistrationRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
@Service
public class CompetitionService {
    private final CompetitionRegistrationRepository registrationRepository;

    public CompetitionService(CompetitionRegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public CompetitionRegistration register(CompetitionRegistration registration) {
        registration.setRegisteredAt(LocalDateTime.now());
        return registrationRepository.save(registration);
    }

    public List<CompetitionRegistration> getRegistrationsByCompetition(Long competitionId) {
        return registrationRepository.findByCompetitionId(competitionId);
    }
}