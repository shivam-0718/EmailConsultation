package com.pmvyas.emailconsultation.advice;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ErrorDetails {
    private LocalDateTime dateTime;
    private String message;
}
