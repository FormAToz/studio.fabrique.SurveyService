package studio.fabrique.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.fabrique.api.response.ResultResponse;
import studio.fabrique.model.User;

@RestController
@RequestMapping("auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<ResultResponse> login(User user) {
        //TODO for admin
        return ResponseEntity.ok(new ResultResponse(true));
    }

    @GetMapping("/logout")
    public ResponseEntity<ResultResponse> logout() {
        //TODO for admin
        return ResponseEntity.ok(new ResultResponse(true));
    }
}