package ar.com.damian.drinkbros_backend.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClientException extends RuntimeException {

    private final  HttpStatus status;
    private final LocalDateTime timestamp;
    private final int errorCode;
    private final String message;
    private final String violatedFields;
    private final String description;
}
