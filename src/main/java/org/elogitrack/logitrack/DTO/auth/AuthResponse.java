package org.elogitrack.logitrack.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String token;
    private Long userId;
    private String nom;
    private String prenom;
    private String email;
    private String role;
}
