package com.hashhub.hashhub2_0.repository;

import com.hashhub.hashhub2_0.models.DocumentEntity;
import com.hashhub.hashhub2_0.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.Document;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {

    Iterable<DocumentEntity> findByEmail(String email);

    Iterable<DocumentEntity> findBySharedWith(String sharedWith);

    DocumentEntity findById(long id);

    DocumentEntity deleteById(long id);






}
