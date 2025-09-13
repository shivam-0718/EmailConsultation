package com.pmvyas.emailconsultation.service;

public interface IEmailService {
    String bookConsultation(String clientName, String clientEmailId, String subject, String body);
}
