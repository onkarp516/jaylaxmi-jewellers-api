package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class InstituteDTDTO {
    private Long id;
    private String instituteName;
    private String mobile;
    private String email;
    private String address;
    private String username;
    private String password;
    private String plainPassword;
    private boolean status;
    private Long createdBy;
    private LocalDateTime createdAt;
    private Long updatedBy;
    private LocalDateTime updatedAt;
}
