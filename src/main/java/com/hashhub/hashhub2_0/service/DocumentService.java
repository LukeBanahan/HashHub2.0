package com.hashhub.hashhub2_0.service;

import com.hashhub.hashhub2_0.dto.DocumentDto;

import java.util.List;

public interface DocumentService {
    List<DocumentDto> findAllDocuments();
}
