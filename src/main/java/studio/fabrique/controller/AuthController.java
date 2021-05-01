package studio.fabrique.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.fabrique.api.request.LoginRequest;
import studio.fabrique.api.response.ResultResponse;
import studio.fabrique.service.AuthorizationService;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthorizationService authorizationService;


    public AuthController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }


    @PostMapping("/login")
    public ResponseEntity<ResultResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authorizationService.login(request));
    }

    @GetMapping("/logout")
    @PreAuthorize("hasAuthority('user:moderate') and hasAuthority('user:write')")
    public ResponseEntity<ResultResponse> logout() {
        return ResponseEntity.ok(authorizationService.logout());
    }
}