package studio.fabrique.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import studio.fabrique.api.response.ResultResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<ResultResponse> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        return ResponseEntity.status(403).body(new ResultResponse(false, ex.getLocalizedMessage()));
    }
}
