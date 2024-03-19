package com.hashhub.hashhub2_0.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DocumentDto {

    private Long id;
    private String name;
    private Long size;
    private String email;
    private String sharedWith;
    private boolean isSigned;
    private boolean isVerified;
    private byte[] content;
    private LocalDateTime uploadedOn;
    private LocalDateTime signedOn;
    private LocalDateTime verifiedOn;
}

