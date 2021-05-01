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

@RestController
@RequestMapping("auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<ResultResponse> login(@RequestBody LoginRequest request) {
        //TODO for admin
        return ResponseEntity.ok(new ResultResponse(true));
    }

    @GetMapping("/logout")
    @PreAuthorize("hasAuthority('user:moderate') and hasAuthority('user:write')")
    public ResponseEntity<ResultResponse> logout() {
        //TODO for admin
        return ResponseEntity.ok(new ResultResponse(true));
    }
}