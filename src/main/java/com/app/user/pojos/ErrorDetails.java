package com.app.user.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    //timestamp
    //message
    //details
    private LocalDateTime timestamp;
    private String message;
    private String details;
}
