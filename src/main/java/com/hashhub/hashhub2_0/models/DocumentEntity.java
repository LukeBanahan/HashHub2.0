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
@Table(name = "documents")
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long size;
    private String email;
    private boolean isSigned;

    @Lob
    private byte[] content;

    private LocalDateTime uploadedOn;
    private LocalDateTime updatedOn;


}
