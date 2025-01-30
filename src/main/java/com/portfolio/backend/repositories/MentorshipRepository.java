package com.portfolio.backend.repositories;

import com.portfolio.backend.models.Mentorship;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface MentorshipRepository extends JpaRepository<Mentorship, Long> {
    List<Mentorship> findByMentorId(Long mentorId);
    List<Mentorship> findByMenteeId(Long menteeId);
}