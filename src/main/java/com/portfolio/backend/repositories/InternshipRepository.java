package com.portfolio.backend.repositories;

import com.portfolio.backend.models.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;;
public interface InternshipRepository extends JpaRepository<Internship, Long> {
    List<Internship> findByType(String type);
    List<Internship> findByLocation(String location);
}