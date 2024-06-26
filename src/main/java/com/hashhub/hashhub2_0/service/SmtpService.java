package com.hashhub.hashhub2_0.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SmtpService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail,
                          String subject,
                          String body){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("hashhubsmtpservice@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);

        System.out.println("Mail sent successfully");


    }
}



//

/***************************************************************************************
 *    Title: Send Email Using Spring Boot | Gmail SMTP | Java Mail Sender
 *    Author: Code With Arjun
 *    Date: 23/07/2021
 *    Availability: https://www.youtube.com/watch?v=ugIUObNHZdo
 *    Accessed: 04/02/2024
 *
 ***************************************************************************************/