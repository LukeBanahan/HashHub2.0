package com.hashhub.hashhub2_0.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "documents")
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long size;
    private String email;
    private String sharedWith;
    private boolean isSigned;
    @Lob
    private byte[] publicKey;
    @Lob
    private byte[] digitalSignature;
    private boolean isVerified;
    @Lob
    private byte[] content;
    private LocalDateTime uploadedOn;
    private LocalDateTime signedOn;
    private LocalDateTime verifiedOn;


}
