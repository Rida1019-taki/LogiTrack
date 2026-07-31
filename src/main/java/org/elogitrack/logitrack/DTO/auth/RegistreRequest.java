package org.elogitrack.logitrack.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.elogitrack.logitrack.enums.RoleUser;

@Data
public class RegistreRequest {
    @NotBlank(message = "user name obligatoire ")
    private String nom;

    @NotBlank(message = "user prenom obligatoire ")
    private String prenom;


    @NotBlank(message = "email obligatoire")
    @Email(message = "email invalid ")
    private String email;

    @NotNull(message = "role obligatoire")
    private RoleUser role;

    @NotBlank(message = "password obligatoire")
    private String password;
}
