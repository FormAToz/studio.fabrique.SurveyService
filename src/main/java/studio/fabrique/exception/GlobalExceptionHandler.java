package studio.fabrique.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import studio.fabrique.api.response.ResultResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ResultResponse> handleAccessDeniedException(AccessDeniedException ex) {
        return handleInternal(HttpStatus.FORBIDDEN, ex.getLocalizedMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<ResultResponse> handleAuthenticationException(AuthenticationException ex) {
        return handleInternal(HttpStatus.FORBIDDEN, ex.getLocalizedMessage());
    }

    @ExceptionHandler(ApplicationException.class)
    public final ResponseEntity<ResultResponse> handleAccessDeniedException(ApplicationException ex) {
        return handleInternal(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
    }

    private ResponseEntity<ResultResponse> handleInternal(HttpStatus status, String message) {
        return new ResponseEntity<>(new ResultResponse(false, message), status);
    }
}
