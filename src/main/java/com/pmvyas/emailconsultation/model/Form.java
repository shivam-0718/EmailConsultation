package com.pmvyas.emailconsultation.model;

import lombok.Data;

/**
 * Model class to capture form data from consultation requests.
 * Contains client information and message details.
 */
@Data
public class Form {
    private String name;
    private String emailId;
    private String subject;
    private String body;
}
