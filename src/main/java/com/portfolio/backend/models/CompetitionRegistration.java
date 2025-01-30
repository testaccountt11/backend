package com.portfolio.backend.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "competition_registrations")
public class CompetitionRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long competitionId;
    private String fullName;
    private String email;
    private String phone;
    private String motivation;
    private LocalDateTime registeredAt;
}