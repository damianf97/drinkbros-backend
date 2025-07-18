package ar.com.damian.drinkbros_backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {
    private LocalDateTime timestamp;
    private int errorCode;
    private String message;
    private String violatedFields;
    private String description;
}
