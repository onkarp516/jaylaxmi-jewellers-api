package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.dto;

import lombok.Data;

@Data
public class AllowanceDTDTO {
    private Long id;
    private String name;
    private Double amount;
    private Boolean allowanceStatus;
    private Boolean status;
    private Long createdBy;
    private String createdAt;
    private Long updatedBy;
    private String updatedAt;
}
