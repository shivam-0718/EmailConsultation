package com.pmvyas.emailconsultation.service;

public interface IEmailService {
    void bookConsultation(String clientName, String clientEmailId, String subject, String body);
}
