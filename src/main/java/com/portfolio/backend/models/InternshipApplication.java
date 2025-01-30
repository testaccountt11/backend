package com.portfolio.backend.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "internship_applications")
public class InternshipApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Internship internship;

    private String fullName;
    private String email;
    private String phone;
    private String coverLetter;
    private String portfolio;
    private String resumePath;
    private LocalDateTime appliedAt;
    private String status = "PENDING";
}