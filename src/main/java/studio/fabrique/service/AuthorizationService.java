package studio.fabrique.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import studio.fabrique.api.request.LoginRequest;
import studio.fabrique.api.response.ResultResponse;

/**
 * Сервис по работе с доступом к приложению
 */
@Service
public class AuthorizationService {

    private final AuthenticationManager authenticationManager;


    public AuthorizationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    /**
     * Метод авторизации пользователей
     * @param request LoginRequest с полями для авторизации
     * @return ResultResponse со значением true и данными об авторизированном пользователе
     * @throw AuthenticationException случае ошибки авторизации
     */
    public ResultResponse login(LoginRequest request) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);
        return new ResultResponse(true);
    }

    /**
     * Метод разлогинивает авторизированного пользователя
     * @return ResultResponse со значением true
     */
    public ResultResponse logout() {

        SecurityContextHolder.clearContext();
        return new ResultResponse(true);
    }
}
