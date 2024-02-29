package com.hashhub.hashhub2_0.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "digital_signatures")
public class DigitalSignatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int documentId;
    private String signature;
    private boolean isVerfied;
    private LocalDateTime signedOn;
}
