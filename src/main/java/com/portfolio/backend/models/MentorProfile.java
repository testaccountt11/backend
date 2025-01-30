package com.portfolio.backend.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "mentor_profiles")
public class MentorProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private User user;
    
    private String title;
    
    @ElementCollection
    private List<String> specialization;
    
    private String experience;
    private Double rating;
    private Integer reviewCount;
    private String hourlyRate;
    private String availability;
    private String location;
    private String bio;
    private String education;
    
    @ElementCollection
    private List<String> languages;
}