package com.portfolio.backend.dto;

import lombok.Data;

@Data
public class GoogleAuthRequest {
    private String email;
    private String fullName;
}
