package org.elogitrack.logitrack.dto.clientdto;
import lombok.Data;

@Data
public class ClientRequestDTO {
    private String nom;
    private String email;
    private String telefone;
    private String ville;
}
