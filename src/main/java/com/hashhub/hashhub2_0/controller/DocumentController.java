package com.hashhub.hashhub2_0.controller;

import com.hashhub.hashhub2_0.dto.DocumentDto;
import com.hashhub.hashhub2_0.dto.RegistrationDto;
import com.hashhub.hashhub2_0.models.DocumentEntity;
import com.hashhub.hashhub2_0.repository.DocumentRepository;
import com.hashhub.hashhub2_0.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;

@Controller
public class DocumentController {

    @Autowired
    private DocumentRepository repo;

    @GetMapping("/document-upload")
    public String documentUploadPage() {
        return "document-upload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("document") MultipartFile multipartFile,
                             RedirectAttributes ra) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        //gets authenticated email from spring security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        DocumentEntity document = new DocumentEntity ();
        document.setName(fileName);
        document.setContent(multipartFile.getBytes());
        document.setSize(multipartFile.getSize());
        document.setSigned(false);
        document.setVerified(false);
        document.setUploadedOn(LocalDateTime.now());

        //populates email field with current authenticated user's email
        document.setEmail(userEmail);

        repo.save(document);

        ra.addFlashAttribute("documentSuccessMsg", "Document upload was successful.");

        return "redirect:/document-upload";
    }

    @GetMapping("/user-documents")
    public String getUserDocuments(Model model) {
        //get current user email from spring security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        // creates an iterable collection of documents and filters by current user email
        Iterable<DocumentEntity> userDocuments =  repo.findByEmail(userEmail);

        model.addAttribute("userDocuments", userDocuments);

        return "user-documents";
    }

    @GetMapping("/shared-documents")
    public String getSharedDocuments(Model model) {
        //get current user email from spring security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        // creates an iterable collection of documents and filters by current user email
        Iterable<DocumentEntity> sharedDocuments =  repo.findBySharedWith(userEmail);

        model.addAttribute("sharedDocuments", sharedDocuments);

        return "shared-documents";
    }


    @PostMapping("/send-document")
    public String sendDocument(@RequestParam("selectedDocumentIdValue") String id,
                               @RequestParam("recipientEmail") String recipientEmail){
        long documentId = Long.parseLong(id);
        DocumentEntity documentEntity = repo.findById(documentId);

        if (documentEntity != null) {
            documentEntity.setSharedWith(recipientEmail);
            repo.save(documentEntity);
        }

        if (documentEntity != null) {
            return "redirect:/user-documents?success";
        } else {
            return "redirect:/user-documents?fail";
        }

    }

    @PostMapping("/sign-document")
    public String signDocument(@RequestParam("selectedDocumentId") String id) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        //Find selected document
        long documentId = Long.parseLong(id);
        DocumentEntity documentEntity = repo.findById(documentId);

        //Hash document with SHA256 algorithm and store it in a new byte array variable called hashedDocument
        byte[] documentToSign = documentEntity.getContent();
        MessageDigest digest = MessageDigest.getInstance("SHA256");
        byte[] hashedDocument = digest.digest(documentToSign);



        //Generate key pair using RSA
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        keyGenerator.initialize(2048);
        KeyPair pair = keyGenerator.generateKeyPair();
        PublicKey publicKey = pair.getPublic();
        PrivateKey privateKey = pair.getPrivate();

        //Encrypt hashed document to produce digital signature
        Signature rsa = Signature.getInstance("SHA256withRSA");
        rsa.initSign(privateKey);
        rsa.update(hashedDocument);
        byte[] digitalSignature = rsa.sign();


        documentEntity.setDigitalSignature(digitalSignature);
        documentEntity.setPublicKey(publicKey.getEncoded());
        documentEntity.setSigned(true);
        documentEntity.setSignedOn(LocalDateTime.now());
        repo.save(documentEntity);

        return "redirect:/user-documents";
    }

    @PostMapping("/verify-document")
    public String verifyDocument(@RequestParam("selectedDocumentId") String id) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException {
        //Find selected document
        long documentId = Long.parseLong(id);
        DocumentEntity documentEntity = repo.findById(documentId);

        byte[] digitalSignatureBytes = documentEntity.getDigitalSignature();
        byte[] publicKeyBytes = documentEntity.getPublicKey();

        byte[] documentToVerify = documentEntity.getContent();
        MessageDigest digest = MessageDigest.getInstance("SHA256");
        byte[] hashedDocument = digest.digest(documentToVerify);



        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);

        signature.update(hashedDocument);

        boolean isVerified = signature.verify(digitalSignatureBytes);

        if (isVerified) {
            documentEntity.setVerified(true);
            documentEntity.setVerifiedOn(LocalDateTime.now());
            repo.save(documentEntity);

            return "redirect:/shared-documents?success";
        } else {
            return "redirect:/shared-documents?fail";
        }

    } //https://www.tabnine.com/code/java/classes/java.security.spec.X509EncodedKeySpec



}




