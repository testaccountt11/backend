package com.portfolio.backend.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SessionRequestDTO {
    private Long mentorId;
    private String topic;
    private String message;
    private LocalDateTime scheduledTime;
    private Integer duration;
}