package com.pmvyas.emailconsultation.service;

import com.pmvyas.emailconsultation.util.AckNumber;
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

    /**
     * Sends consultation request email from client to consultant.
     * Formats the message with client details and sends via configured SMTP.
     * This will be sent to consultant from Client (when client fills the details)
     */
    //This email will be sent from consultant to client with some acknowledgement number.
    @Override
    public void sendEmailToClient(String clientName, String clientEmailId, String subject, String body) {
        this.ackNumber = AckNumber.ackNumber;

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(consultantEmailId);
        message.setTo(clientEmailId);
        message.setSubject(subject);
        message.setText("""
               Hello %s,
               
               Thank you for reaching out to us.

               Your consultation request has been successfully received by our consultant team. \s
               Your acknowledgement number is: %s.

               Our team will connect with you within 2 working days. \s
               Kindly wait for further communication from us.

               Regards, \s
               Consultant Team \s
               PMV \s
               +91 12345 67890

               """.formatted(clientName, ackNumber));

        mailSender.send(message);
    }

    @Override
    public void sendEmailToConsultant(String consultantName,String subject, String body, String clientEmailId) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(this.consultantEmailId);
        message.setTo(this.consultantEmailId);
        message.setSubject(subject);
        message.setText("""
                Hello PM Vyas,
                
                You have received a new consultation request from %s.
        
                The details of the consultation are as follows:
        
                Subject: %s
                Message: %s
                Acknowledgement Number: %s
                Email id: %s
        
                Regards,
                Contact Team
                PMV
                +91 12345 67890

                """.formatted(consultantName, subject, body, ackNumber, clientEmailId));

        mailSender.send(message);
    }
    //This email will be sent from consultant to himself for record about client consultation.
}
