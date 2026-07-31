package org.elogitrack.logitrack.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "email obligatoire ")
    @Email(message = "email invalid ")
    private String email;

    @NotBlank(message = "password invalid ")
    private String password;
}
