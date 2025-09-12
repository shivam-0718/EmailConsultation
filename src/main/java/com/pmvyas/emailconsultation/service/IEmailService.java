package com.pmvyas.emailconsultation.service;

public interface IEmailService {
    void sendEmailToClient(String clientName, String clientEmailId, String subject, String body);
    void sendEmailToConsultant(String consultantName, String subject, String body, String clientEmailId);
}
