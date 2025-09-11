package com.pmvyas.emailconsultation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Sends consultation request email from client to consultant.
     * Formats the message with client details and sends via configured SMTP.
     * This will be sent to consultant from Client (when client fills the details)
     */
    @Override
    public void sendEmail(String name, String recipient, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText("Hello." + "\n There is a request from " + name +
                ". Check the message below: \n" + body + "\n Thanks and Regards");

        mailSender.send(message);
    }
}
