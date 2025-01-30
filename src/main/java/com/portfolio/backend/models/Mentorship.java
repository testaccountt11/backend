package com.portfolio.backend.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "mentorships")
public class Mentorship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User mentor;

    @ManyToOne
    private User mentee;

    private String topic;
    private String description;
    private LocalDateTime scheduledTime;
    private Integer duration; // in minutes
    private String status; // PENDING, ACCEPTED, COMPLETED, CANCELLED
    private String meetingLink;
}