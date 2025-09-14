# Email Consultation | Spring Boot Email Service

A comprehensive Spring Boot RESTful web application for sending emails through SMTP integration with advanced email management capabilities. This application provides a robust API for organizations to efficiently send consultation emails and notifications while maintaining detailed email communications.

## 🚀 Features

- **Email Consultation**: Complete email sending operations with text and HTML support.
- **SMTP Integration**: Secure email delivery through Gmail SMTP with TLS/SSL encryption.
- **Advanced Configuration**: Flexible SMTP configuration for multiple email providers.
- **Security**: App Password authentication and secure email transmission.

## 📧 About Spring Email

Spring Email is a powerful feature of the Spring Framework that provides comprehensive support for sending emails through various protocols like SMTP. It offers a high-level abstraction through the `JavaMailSender` interface, which simplifies the process of composing and sending emails in Java applications. The Spring Email module handles connection management, message formatting, attachment handling, and provides template-based email generation capabilities. This makes it easy to integrate email functionality into enterprise applications while maintaining clean separation of concerns and following Spring's dependency injection principles.

## 🛠️ Tech Stack

This project leverages a robust set of technologies across different layers to ensure scalability, maintainability, and enterprise-grade performance.

| Category              | Technology           | Description                                         |
|-----------------------|----------------------|-----------------------------------------------------|
| **Backend**           | Java 17+             | Core programming language                           |
|                       | Spring Boot 3.x      | Opinionated application framework                   |
|                       | Spring MVC           | Robust web layer framework                          |
|                       | Spring Mail          | Email integration framework                         |
|                       | JavaMail API         | Standard email API for Java                         |
| **Email Services**    | Gmail SMTP           | Secure email delivery service                       |
|                       | TLS/SSL Encryption   | Secure email transmission                           |
| **Tools & Libraries** | Lombok               | Reduces boilerplate code (e.g., getters/setters)    |
|                       | Maven                | Project dependency management and build automation  |
|                       | Spring Boot DevTools | Enhances development productivity and hot-reloading |

## 📋 Prerequisites

| Requirement       | Version/Details                              |
|-------------------|----------------------------------------------|
| **Java**          | 17 or higher                                 |
| **Maven**         | 3.6+                                         |
| **Gmail Account** | With 2FA enabled for App Password generation |
| **IDE**           | IntelliJ IDEA, Eclipse, VS Code              |

## 📁 Project Structure

```
EmailConsultation/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/pmvyas/emailconsultation/
│   │   │       ├── advice/
│   │   │       │   ├── EmailControllerAdvice.java
│   │   │       │   └── ErrorDetails.java
│   │   │       ├── configuration/
│   │   │       │   └── Config.java
│   │   │       ├── controller/
│   │   │       │   └── EmailController.java
│   │   │       ├── model/
│   │   │       │   └── Form.java
│   │   │       ├── service/
│   │   │       │   ├── EmailService.java
│   │   │       │   └── IEmailService.java
│   │   │       ├── util/
│   │   │       │   └── AcknowledgementNumber.java
│   │   │       └── EmailConsultationApplication.java
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       └── application.properties
│   └── test/
├── pom.xml
├── README.md
└── LICENSE
```

## 🚀 Steps to Use Spring Email

### Step 1: Get Spring Email Dependency from Spring Initializer

1. Add the Spring Email dependency to your project by visiting [Spring Initializer](https://start.spring.io/).
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-mail</artifactId>
   </dependency>
   ```
2. Add all the necessary dependencies as per your requirements.
3. Generate and download the project.

**Alternative Installation via Command Line**
```bash
git clone https://github.com/shivam-0718/EmailConsultation.git
cd EmailConsultation
mvn clean install
mvn spring-boot:run
```

### Step 2: Add Details in application.properties

Configure your email settings in `application.properties`:

```properties
# Application Configuration
spring.application.name=EmailConsultation

# SMTP Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# Server Configuration
server.port=8080
server.servlet.context-path=/email-service

# Additional SMTP Properties
spring.mail.properties.mail.smtp.connectiontimeout=5000
spring.mail.properties.mail.smtp.timeout=3000
spring.mail.properties.mail.smtp.writetimeout=5000
```

#### 📝 Property Explanations:

| Property                                           | Description                                             | Example Values                                                                       |
|----------------------------------------------------|---------------------------------------------------------|--------------------------------------------------------------------------------------|
| `spring.application.name`                          | Sets the name of your Spring Boot application           | `EmailConsultation`                                                                  |
| `spring.mail.host`                                 | SMTP server hostname                                    | Gmail: `smtp.gmail.com`, Outlook: `smtp.office365.com`, Yahoo: `smtp.mail.yahoo.com` |
| `spring.mail.port`                                 | SMTP server port number                                 | `587` (TLS), `465` (SSL), `25` (Unsecured)                                           |
| `spring.mail.username`                             | Your email address that will be used as the sender      | `your-email@gmail.com`                                                               |
| `spring.mail.password`                             | App-specific password (NOT your regular Gmail password) | 16-character App Password                                                            |
| `spring.mail.properties.mail.smtp.auth`            | Enables SMTP authentication                             | `true` (Enable authentication), `false` (Disable authentication)                     |
| `spring.mail.properties.mail.smtp.starttls.enable` | Enables TLS encryption for secure communication         | `true` (Enable TLS), `false` (Disable TLS)                                           |

### Step 3: Generate Gmail App Password

⚠️ **Critical Security Note**: Never use your regular Gmail password for application authentication. Always use App Passwords for enhanced security.

#### 🔐 How to Get spring.mail.password Using App Password:

**Step 1: Enable 2-Factor Authentication**
1. Navigate to [Google Account Security](https://myaccount.google.com/security).
2. Under "Signing in to Google," click on "2-Step Verification."
3. Follow the setup process if not already enabled.
4. Verify your phone number and backup options.

**Step 2: Generate App Password**
1. Go to [App Passwords](https://myaccount.google.com/apppasswords).
2. You might need to sign in again for security.
3. Select "Mail" as the app type.
4. Select "Other (Custom name)" as the device.
5. Enter a descriptive name: "Spring Boot Email Service."
6. Click "Generate."
7. Copy the 16-character password (format: `abcd efgh ijkl mnop`).

**Step 3: Configure App Password**
1. Use this password in the `spring.mail.password` property.
2. **Never share** this password or commit it to version control.
3. Consider using environment variables for production:
   ```properties
   spring.mail.password=${EMAIL_APP_PASSWORD}
   ```

### Step 4: Initiate JavaMailSender Bean in Config File

Create a configuration class to set up the JavaMailSender bean and take values from application.properties.

### Step 5: Use JavaMailSender in EmailService Class

Create a comprehensive service class to handle various email operations.

## 🔍 Troubleshooting

### Common Issues and Solutions:

| Issue                       | Cause                                          | Solution                                                                                          |
|-----------------------------|------------------------------------------------|---------------------------------------------------------------------------------------------------|
| **Authentication Failed**   | Incorrect credentials or regular password used | ✅ Enable 2FA and use App Password<br>✅ Verify username and password in properties                 |
| **Connection Timeout**      | Network/Firewall restrictions                  | ✅ Check firewall settings<br>✅ Verify SMTP host and port<br>✅ Try alternative ports (465 for SSL) |
| **TLS/SSL Issues**          | Incorrect encryption settings                  | ✅ Use `starttls.enable=true` for port 587<br>✅ Use `ssl.enable=true` for port 465                 |
| **Invalid Addresses**       | Malformed email addresses                      | ✅ Validate email format<br>✅ Check for typos in recipient addresses                               |
| **Large Attachment Issues** | File size limits exceeded                      | ✅ Check ISP attachment limits<br>✅ Compress large files<br>✅ Use cloud storage links instead      |

## 🔮 Future Enhancements

- [ ] **Documentation**: Implementation of OpenAPI / Swagger UI documentation within code. 
- [ ] **Template Support**: Dynamic email templates with Thymeleaf integration or using front-end technologies.
- [ ] **Attachment Support**: Send emails with file attachments and media content.
- [ ] **Testing**: JUnit and Mockito integration testing.
- [ ] **Security**: OAuth2 integration for email providers.
- [ ] **Email Templates**: Rich HTML template library.
- [ ] **Analytics**: Email delivery tracking and statistics.
- [ ] **Multi-Provider**: Support for multiple SMTP providers.
- [ ] **Email Validation**: Real-time email address validation.
- [ ] **Bulk Operations**: Mass email sending with throttling.

## 🤝 Contributing

1. Fork the repository.
2. Create your feature branch (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Open a Pull Request.

## 👨‍💻 Author

**Shivam Vyas**
- GitHub: [@shivam-0718](https://github.com/shivam-0718)
- LinkedIn: [Shivam Vyas](https://www.linkedin.com/in/shivam-vyas-1807/)
- Email: shivam.vyas.1807@gmail.com

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework and email integration.
- Gmail team for providing reliable SMTP services.
- Thymeleaf team for powerful template processing.
- All contributors and testers in the email service community.