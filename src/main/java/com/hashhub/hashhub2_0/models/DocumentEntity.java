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
    private int documentId;
    private int userId;
    private String fileName;
    private String filePath;
    private boolean isSigned;
    private LocalDateTime uploadedOn;
    private LocalDateTime updatedOn;


}
