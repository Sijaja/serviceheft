package dev.sijaja.serviceheft.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sijaja.serviceheft.dto.LoginRequestDto;
import dev.sijaja.serviceheft.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import dev.sijaja.serviceheft.model.Owner;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> postMethodName(@RequestBody LoginRequestDto request, HttpServletResponse response) {
        try {
            Owner owner = authService.login(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(owner);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("Invalid email or password");
        }
        return response;
    }
    
}
