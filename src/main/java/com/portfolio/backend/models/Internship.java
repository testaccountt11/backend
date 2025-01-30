package com.portfolio.backend.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.*;
@Data
@Entity
@Table(name = "internships")
public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String company;
    private String location;
    private String type;
    private String duration;
    private String description;
    
    @ElementCollection
    private List<String> requirements;
    
    private String postedDate;
    private String logo;
    private String applicationLink;
}