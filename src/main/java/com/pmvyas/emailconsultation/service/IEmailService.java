package com.pmvyas.emailconsultation.service;

public interface IEmailService {
    void sendEmailToClient(String clientName, String clientEmailId, String subject, String body);
}
