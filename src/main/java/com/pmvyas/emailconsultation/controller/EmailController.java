package com.pmvyas.emailconsultation.controller;

import com.pmvyas.emailconsultation.model.Form;
import com.pmvyas.emailconsultation.service.IEmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private IEmailService service;

    /**
     * Handles POST requests to send consultation emails.
     * Takes form data from client and sends email to consultant.
     */

    @PostMapping("/contact")
    public ResponseEntity<Map<String, String>> contact(@Valid @RequestBody Form form) {
        String acknowledgementNumber = service.bookConsultation(
                form.getName(), form.getEmailId(), form.getSubject(), form.getBody());

        Map<String, String> response = new HashMap<>();
        response.put("message", "Email sent successfully!");
        response.put("acknowledgement number", acknowledgementNumber);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
