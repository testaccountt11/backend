// backend/src/main/java/com/portfolio/backend/controllers/CourseController.java
package com.portfolio.backend.controllers;

import com.portfolio.backend.models.Course;
import com.portfolio.backend.models.CourseRegistration;
import com.portfolio.backend.services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String level) {
        if (category != null) {
            return ResponseEntity.ok(courseService.getCoursesByCategory(category));
        } else if (level != null) {
            return ResponseEntity.ok(courseService.getCoursesByLevel(level));
        }
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerForCourse(@RequestBody CourseRegistration registration) {
        try {
            registration.setRegisteredAt(LocalDateTime.now());
            CourseRegistration saved = courseService.register(registration);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.createCourse(course));
    }
}