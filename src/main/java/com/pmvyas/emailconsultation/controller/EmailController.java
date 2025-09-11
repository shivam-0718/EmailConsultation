package com.pmvyas.emailconsultation.controller;

import com.pmvyas.emailconsultation.model.Form;
import com.pmvyas.emailconsultation.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String contact(@RequestBody Form form) {
        service.sendEmail(form.getName(), "satyam.vyas.04@gmail.com", form.getSubject(), form.getBody());
        return "Email sent successfully!";
    }
}
