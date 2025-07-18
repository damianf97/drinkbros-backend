package ar.com.damian.drinkbros_backend.exception;

import ar.com.damian.drinkbros_backend.util.Constants;
import ar.com.damian.drinkbros_backend.util.MessageBundle;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setErrorCode(404);
        errorDetail.setMessage(MessageBundle.RESOURCE_NOT_FOUND);
        errorDetail.setDescription(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetail);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDetail> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setErrorCode(401);
        errorDetail.setMessage(MessageBundle.USER_PASSWORD_INCORRECT);
        errorDetail.setDescription(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetail);
    }

    @ExceptionHandler(AccountStatusException.class)
    public ResponseEntity<ErrorDetail> handleAccountStatusException(AccountStatusException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setErrorCode(403);
        errorDetail.setMessage(MessageBundle.ACCOUNT_LOKECD);
        errorDetail.setDescription(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDetail);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetail> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setErrorCode(401);
        errorDetail.setMessage(MessageBundle.UNAUTHORIZED);
        errorDetail.setDescription(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetail);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorDetail> handleSignatureException(SignatureException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setErrorCode(403);
        errorDetail.setMessage(MessageBundle.JWT_INVALID);
        errorDetail.setDescription(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDetail);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorDetail> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setErrorCode(403);
        errorDetail.setMessage(MessageBundle.JWT_TOKEN_EXPIRED);
        errorDetail.setDescription(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDetail);
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {
        ProblemDetail errorDetail = null;

        // TODO send this stack trace to an observability tool
        exception.printStackTrace();

        errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
        errorDetail.setProperty(Constants.STRING_DESCRIPTION, MessageBundle.INTERNAL_ERROR);

        return errorDetail;
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetail> handleBadRequestException(BadRequestException ex, WebRequest request) {
        ErrorDetail errorResponse = new ErrorDetail(LocalDateTime.now(ZoneOffset.UTC), 400, ex.getMessage(), request.getDescription(false), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}