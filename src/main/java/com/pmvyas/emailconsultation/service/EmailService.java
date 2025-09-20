package com.pmvyas.emailconsultation.service;

import com.pmvyas.emailconsultation.util.AcknowledgementNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    @Value("${spring.mail.username}")
    private String consultantEmailId;

    @Autowired
    private JavaMailSender mailSender;

    private String ackNumber;

    @Override
    public void bookConsultation(String clientName, String clientEmailId, String subject, String body) {
        sendEmailToClient(clientName, clientEmailId, subject, body);
        sendEmailToConsultant(clientName, clientEmailId, body, subject);
    }

    private void sendEmailToClient(String clientName, String clientEmailId, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(consultantEmailId);
        message.setTo(clientEmailId);
        message.setSubject(subject);
        message.setText("""
               Hello %s,
               
               Thank you for reaching out to us.

               Your consultation request has been successfully received by our consultant team. \s

               Our team will connect with you within 2 working days. \s
               Kindly wait for further communication from us.

               Regards, \s
               Consultant Team \s

               """.formatted(clientName));

        mailSender.send(message);
        sendEmailToConsultant(clientName, clientEmailId, body, subject);
    }

    private void sendEmailToConsultant(String clientName, String clientEmailId, String body, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(this.consultantEmailId);
        message.setTo(this.consultantEmailId);
        message.setSubject(subject);
        message.setText("""
                Hello PM Vyas,
                
                You have received a new consultation request from %s. \s
        
                The details of the consultation are as follows:
        
                Subject: %s \s
                Message: %s \s
                Email id: %s \s
        
                Regards,
                Contact Team
                PMV
                +91 12345 67890

                """.formatted(clientName, subject, body, clientEmailId));

        mailSender.send(message);
    }
    //This email will be sent from consultant to himself for record about client consultation.
}
