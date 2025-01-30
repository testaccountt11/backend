package com.portfolio.backend.models;
import java.util.List;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String instructor;
    private String level;
    private String duration;
    private Double rating;
    private Integer studentsCount;
    private String description;
    private String image;
    private String price;
    private String category;
    
    @ElementCollection
    private List<String> topics;
}