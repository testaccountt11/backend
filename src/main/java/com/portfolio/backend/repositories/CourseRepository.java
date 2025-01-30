
package com.portfolio.backend.repositories;
import java.util.List;
import com.portfolio.backend.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCategory(String category);
    List<Course> findByLevel(String level);
}