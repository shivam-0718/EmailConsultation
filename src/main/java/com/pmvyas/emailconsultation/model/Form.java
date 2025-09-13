package com.pmvyas.emailconsultation.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Model class to capture form data from consultation requests.
 * Contains client information and message details.
 */
@Data
public class Form {

    @NotBlank(message = "Full name is required.")
    @Size(min = 10, max = 50, message = "Full name must be between 10 characters and 50 characters.")
    private String name;

    @NotBlank(message = "Email is required.")
    @Email(message = "Please provide a valid email address.")
    private String emailId;

    @NotBlank(message = "Enter the subject of the request.")
    @Size(max = 100, message = "Subject cannot exceed 100 characters.")
    private String subject;

    @NotBlank(message = "Enter the details of your request.")
    @Size(min = 100, max = 500, message = "Request details must be between 100 characters and 500 characters.")
    private String body;
}
