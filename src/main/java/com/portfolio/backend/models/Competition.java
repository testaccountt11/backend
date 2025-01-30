package com.portfolio.backend.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "competitions")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String prize;
    private String requirements;
    private Integer participantLimit;
    private String status;
    private String image;
}