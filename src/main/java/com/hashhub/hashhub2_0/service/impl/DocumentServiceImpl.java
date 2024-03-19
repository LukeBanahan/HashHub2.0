package com.hashhub.hashhub2_0.service.impl;

import com.hashhub.hashhub2_0.dto.DocumentDto;
import com.hashhub.hashhub2_0.models.DocumentEntity;
import com.hashhub.hashhub2_0.repository.DocumentRepository;
import com.hashhub.hashhub2_0.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.Document;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentServiceImpl implements DocumentService {
    private DocumentRepository documentRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public List<DocumentDto> findAllDocuments() {
        List<DocumentEntity> documents = documentRepository.findAll();
        return documents.stream().map((document) -> mapToDocumentDto(document)).collect(Collectors.toList());
    }

    private DocumentDto mapToDocumentDto(DocumentEntity document) {
        DocumentDto documentDto = DocumentDto.builder()
                .id(document.getId())
                .name(document.getName())
                .size(document.getSize())
                .email(document.getEmail())
                .sharedWith(document.getSharedWith())
                .isSigned(document.isSigned())
                .isVerified(document.isVerified())
                .content(document.getContent())
                .uploadedOn(document.getUploadedOn())
                .signedOn(document.getSignedOn())
                .verifiedOn(document.getVerifiedOn())
                .build();
        return documentDto;
    }
}

