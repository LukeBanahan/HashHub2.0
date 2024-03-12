package com.hashhub.hashhub2_0.controller;

import com.hashhub.hashhub2_0.models.DocumentEntity;
import com.hashhub.hashhub2_0.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDateTime;

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
        String username = authentication.getName();

        DocumentEntity document = new DocumentEntity ();
        document.setName(fileName);
        document.setContent(multipartFile.getBytes());
        document.setSize(multipartFile.getSize());
        document.setSigned(false);
        document.setUploadedOn(LocalDateTime.now());

        //populates username field with current authenticated user's email
        document.setEmail(username);

        repo.save(document);

        ra.addFlashAttribute("documentSuccessMsg", "Document upload was successful.");

        return "redirect:/document-upload";
    }


}
