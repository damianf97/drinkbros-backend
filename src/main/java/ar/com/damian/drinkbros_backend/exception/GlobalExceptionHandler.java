package ar.com.damian.drinkbros_backend.exception;

import ar.com.damian.drinkbros_backend.model.entity.User;
import ar.com.damian.drinkbros_backend.util.MessageBundle;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.event.Level;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    public static final String UNKNOWN_ERROR_MESSAGE = "unknown error message";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setErrorCode(404);
        errorDetail.setMessage(MessageBundle.RESOURCE_NOT_FOUND);
        errorDetail.setDescription(ex.getMessage());
        logException(ex, request, Level.ERROR);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetail);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDetail> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setErrorCode(401);
        errorDetail.setMessage(MessageBundle.USER_PASSWORD_INCORRECT);
        errorDetail.setDescription(ex.getMessage());
        logException(ex, request, Level.ERROR);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetail);
    }

    @ExceptionHandler(AccountStatusException.class)
    public ResponseEntity<ErrorDetail> handleAccountStatusException(AccountStatusException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setErrorCode(403);
        errorDetail.setMessage(MessageBundle.ACCOUNT_LOKECD);
        errorDetail.setDescription(ex.getMessage());
        logException(ex, request, Level.ERROR);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDetail);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetail> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setErrorCode(401);
        errorDetail.setMessage(MessageBundle.UNAUTHORIZED);
        errorDetail.setDescription(ex.getMessage());
        logException(ex, request, Level.WARN);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetail);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorDetail> handleSignatureException(SignatureException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setErrorCode(403);
        errorDetail.setMessage(MessageBundle.JWT_INVALID);
        errorDetail.setDescription(ex.getMessage());
        logException(ex, request, Level.WARN);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDetail);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorDetail> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setErrorCode(403);
        errorDetail.setMessage(MessageBundle.JWT_TOKEN_EXPIRED);
        errorDetail.setDescription(ex.getMessage());
        logException(ex, request, Level.WARN);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDetail);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleGenericException(Exception ex, WebRequest request) {
        logException(ex, request, Level.ERROR);
        ErrorDetail errorResponse = new ErrorDetail(LocalDateTime.now(ZoneOffset.UTC), 0, ex.getMessage(), request.getDescription(false), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetail> handleBadRequestException(BadRequestException ex, WebRequest request) {
        logException(ex, request, Level.WARN);
        ErrorDetail errorResponse = new ErrorDetail(LocalDateTime.now(ZoneOffset.UTC), 400, ex.getMessage(), request.getDescription(false), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    private void logException(Exception ex, WebRequest request, Level level) {
        String mensaje = String.format("[%s] %s --- Request URI: %s --- User: %s",
                ex.getClass().getSimpleName(),
                ex.getMessage() != null ? ex.getMessage() : UNKNOWN_ERROR_MESSAGE,
                request.getDescription(false).replace("uri=", ""),
                getUserSessionInfo()
        );

        log.atLevel(level).setCause(ex).log(mensaje);
    }

    private String getUserSessionInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null || !(authentication.getPrincipal() instanceof User userSession)) {
            return "No user session available";
        }

        return String.format("UserSession{drinkBrotherId=%s, userId=%s, email=%s}",
                userSession.getDrinkBrotherId(),
                userSession.getUserId(),
                userSession.getEmail());
    }
}