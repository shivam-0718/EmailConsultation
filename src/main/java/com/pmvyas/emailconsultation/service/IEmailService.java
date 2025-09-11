package com.pmvyas.emailconsultation.service;

public interface IEmailService {
    void sendEmail(String name, String recipient, String subject, String body);
}
