package com.portfolio.backend.repositories;

import com.portfolio.backend.models.MentorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorProfileRepository extends JpaRepository<MentorProfile, Long> {
    MentorProfile findByUserId(Long userId);
}