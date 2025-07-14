package ar.com.damian.drinkbros_backend.exception;

import ar.com.damian.drinkbros_backend.util.Constants;
import ar.com.damian.drinkbros_backend.util.MessageBundle;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
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

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setCode(404);
        errorDetail.setMessage(ex.getMessage());
        errorDetail.setDescription(MessageBundle.RESOURCE_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetail);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDetail> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setCode(401);
        errorDetail.setMessage(ex.getMessage());
        errorDetail.setDescription(MessageBundle.USER_PASSWORD_INCORRECT);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetail);
    }

    @ExceptionHandler(AccountStatusException.class)
    public ResponseEntity<ErrorDetail> handleAccountStatusException(AccountStatusException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setCode(403);
        errorDetail.setMessage(ex.getMessage());
        errorDetail.setDescription(MessageBundle.ACCOUNT_LOKECD);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDetail);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetail> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setCode(401);
        errorDetail.setMessage(ex.getMessage());
        errorDetail.setDescription(MessageBundle.UNAUTHORIZED);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetail);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorDetail> handleSignatureException(SignatureException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setCode(403);
        errorDetail.setMessage(ex.getMessage());
        errorDetail.setDescription(MessageBundle.JWT_INVALID);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDetail);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorDetail> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setCode(403);
        errorDetail.setMessage(ex.getMessage());
        errorDetail.setDescription(MessageBundle.JWT_TOKEN_EXPIRED);
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
}