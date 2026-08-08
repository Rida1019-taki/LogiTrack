package org.elogitrack.logitrack.service;

import lombok.RequiredArgsConstructor;
import org.elogitrack.logitrack.dto.auth.AuthResponse;
import org.elogitrack.logitrack.dto.auth.LoginRequest;
import org.elogitrack.logitrack.dto.auth.RegistreRequest;
import org.elogitrack.logitrack.model.User;
import org.elogitrack.logitrack.repository.UserRepository;
import org.elogitrack.logitrack.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegistreRequest request) {

        User user = new User();

        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        repository.save(user);

        String token = jwtService.genereteToken(
                user.getEmail(),
                user.getRole().name(),
                user.getId(),
                null
        );

        return new AuthResponse(
                token,
                user.getId(),
                user.getNom(),
                user.getPrenom(),
                user.getEmail(),
                user.getRole().name()
        );
    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        String token = jwtService.genereteToken(
                user.getEmail(),
                user.getRole().name(),
                user.getId(),
                null
        );

        return new AuthResponse(
                token,
                user.getId(),
                user.getNom(),
                user.getPrenom(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}