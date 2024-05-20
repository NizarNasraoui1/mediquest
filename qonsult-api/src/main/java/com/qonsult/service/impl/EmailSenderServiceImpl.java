package com.qonsult.service.impl;

import com.qonsult.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {



    @Autowired
    private JavaMailSender mailSender;

    @Override
    @Async
    public void sendEmail(String toEmail,
                                String subject,
                                String body
    ){

        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(toEmail);
            helper.setFrom("nizarnasraouif@gmail.com");
            helper.setSubject(subject);
            helper.setText(body, true);
            mailSender.send(message);
        }
        catch (MessagingException messagingException){
            messagingException.printStackTrace();
        }


    }

}