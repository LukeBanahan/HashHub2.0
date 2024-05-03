

package com.hashhub.hashhub2_0.repository;

import com.hashhub.hashhub2_0.models.DocumentEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.swing.text.Document;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

public class DocumentRepositoryTests {
    @Autowired
    private DocumentRepository documentRepository;

    //test that save function is working in DocumentRepository
    @Test
    public void DocumentRepository_Save_ReturnSavedDocuments() {

        //Arrange
        DocumentEntity documentEntity = DocumentEntity.builder()
                .id(1L)
                .name("testDocument")
                .size(1024L)
                .email("test@test.com")
                .sharedWith("shareTest")
                .isSigned(true)
                .isVerified(true)
                .build();

        //Act
        DocumentEntity savedDocumentEntity = documentRepository.save(documentEntity);


        //Assert
        Assertions.assertNotNull(savedDocumentEntity);
    }

    @Test //test that find by Id function works as expected.
    public void DocumentRepository_findDocumentById_returnDocumentById() {
        //Arrange
        DocumentEntity documentEntity = DocumentEntity.builder()
                .id(1L)
                .name("testDocument")
                .size(1024L)
                .email("test@test.com")
                .sharedWith("shareTest")
                .isSigned(true)
                .isVerified(true)
                .build();

        //Act
        documentRepository.save(documentEntity);
        DocumentEntity foundDocument = documentRepository.findById(1L);


        //Assert
        Assertions.assertEquals(documentEntity, foundDocument);


    }

    @Test
    public void DocumentRepository_DeleteById_ReturnNullDocument(){
        //Arrange
        DocumentEntity documentEntity = DocumentEntity.builder()
                .id(1L)
                .name("testDocument")
                .size(1024L)
                .email("test@test.com")
                .sharedWith("shareTest")
                .isSigned(true)
                .isVerified(true)
                .build();

        DocumentEntity documentEntity2 = DocumentEntity.builder()
                .id(2L)
                .name("testDocument2")
                .size(1024L)
                .email("test2@test.com")
                .sharedWith("shareTest2")
                .isSigned(true)
                .isVerified(true)
                .build();

        //Act
        documentRepository.save(documentEntity);
        documentRepository.save(documentEntity2);

        //check that both documents saved
        Assertions.assertNotNull(documentEntity);
        Assertions.assertNotNull(documentEntity2);

        //delete document by id
        documentRepository.deleteById(documentEntity2.getId());

        //assert that the correct document was deleted
        Assertions.assertNotNull(documentEntity);

        //attempt to retrieve deleted document and assert that it is deleted.
        DocumentEntity deletedDocument = documentRepository.findById(documentEntity2.getId()).orElse(null);
        Assertions.assertNull(deletedDocument);

        // check that remaining document is still present, to confirm only specified document was deleted.
        DocumentEntity remainingDocument = documentRepository.findById(documentEntity.getId()).orElse(null);
        Assertions.assertNotNull(remainingDocument);

    }
}
//https://www.youtube.com/watch?v=jqwZthuBmZY&list=PL82C6-O4XrHcg8sNwpoDDhcxUCbFy855E