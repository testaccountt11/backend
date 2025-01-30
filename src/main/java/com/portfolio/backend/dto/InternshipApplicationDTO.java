package com.portfolio.backend.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class InternshipApplicationDTO {
    private Long internshipId;
    private String fullName;
    private String email;
    private String phone;
    private String coverLetter;
    private String portfolio;
    private MultipartFile resume;
}