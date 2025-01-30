package com.portfolio.backend.repositories;

import com.portfolio.backend.models.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
    List<CourseRegistration> findByCourseId(Long courseId);
}