package com.hashhub.hashhub2_0.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DigitalSignatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int documentId;
    private String signature;
    private boolean isVerfied;
    private LocalDateTime signedOn;
}
