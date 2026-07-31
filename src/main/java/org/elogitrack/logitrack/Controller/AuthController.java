package org.elogitrack.logitrack.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elogitrack.logitrack.dto.auth.AuthResponse;
import org.elogitrack.logitrack.dto.auth.LoginRequest;
import org.elogitrack.logitrack.dto.auth.RegistreRequest;
import org.elogitrack.logitrack.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegistreRequest request) {

        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(authService.login(request));
    }

}