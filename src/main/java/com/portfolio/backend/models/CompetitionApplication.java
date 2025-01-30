package com.portfolio.backend.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "competition_applications")
public class CompetitionApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Competition competition;
    
    private String fullName;
    private String email;
    private String phone;
    private String motivation;
    private String submissionLink;
    private String resumePath;
    private LocalDateTime appliedAt;
    private String status = "PENDING";
}