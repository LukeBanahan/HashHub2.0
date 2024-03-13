package com.hashhub.hashhub2_0.repository;

import com.hashhub.hashhub2_0.models.DocumentEntity;
import com.hashhub.hashhub2_0.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {

    Iterable<DocumentEntity> findByEmail(String email);
}
