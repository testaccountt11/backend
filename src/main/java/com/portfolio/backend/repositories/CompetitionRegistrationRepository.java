package com.portfolio.backend.repositories;

import com.portfolio.backend.models.CompetitionRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface CompetitionRegistrationRepository extends JpaRepository<CompetitionRegistration, Long> {
    List<CompetitionRegistration> findByCompetitionId(Long competitionId);
}