// backend/src/main/java/com/portfolio/backend/services/CourseService.java
package com.portfolio.backend.services;

import com.portfolio.backend.models.Course;
import com.portfolio.backend.models.CourseRegistration;
import com.portfolio.backend.repositories.CourseRegistrationRepository;
import com.portfolio.backend.repositories.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CourseService {
  private final CourseRepository courseRepository;
    private final CourseRegistrationRepository registrationRepository;

    public CourseService(CourseRepository courseRepository, 
                        CourseRegistrationRepository registrationRepository) {
        this.courseRepository = courseRepository;
        this.registrationRepository = registrationRepository;
    }


    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getCoursesByCategory(String category) {
        return courseRepository.findByCategory(category);
    }

    public List<Course> getCoursesByLevel(String level) {
        return courseRepository.findByLevel(level);
    }
      public CourseRegistration register(CourseRegistration registration) {
        Course course = courseRepository.findById(registration.getCourseId())
            .orElseThrow(() -> new RuntimeException("Course not found"));
            
        return registrationRepository.save(registration);
    }

    public List<CourseRegistration> getRegistrationsByCourse(Long courseId) {
        return registrationRepository.findByCourseId(courseId);
    }
}