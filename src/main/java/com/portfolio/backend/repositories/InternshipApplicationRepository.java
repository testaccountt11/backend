package com.portfolio.backend.repositories;

import com.portfolio.backend.models.InternshipApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InternshipApplicationRepository extends JpaRepository<InternshipApplication, Long> {
    List<InternshipApplication> findByInternshipId(Long internshipId);
}