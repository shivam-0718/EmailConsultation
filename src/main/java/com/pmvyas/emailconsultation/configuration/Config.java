package com.pmvyas.emailconsultation.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class Config {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    /**
     * Creates and configures JavaMailSender bean for sending emails through Gmail SMTP.
     * Sets up authentication and secure connection properties for Gmail.
     */
    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        //Properties for starting STARTTLS Command
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");     // "Use the standard email delivery method"
        props.put("mail.smtp.auth", "true");              // "Yes, check ID and password"
        props.put("mail.smtp.starttls.enable", "true");   // "Turn on security shield"
        props.put("mail.smtp.starttls.required", "true"); // "Security shield is MANDATORY"
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // "Trust Gmail's security certificate"

        return mailSender;
    }
}
