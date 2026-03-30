package org.elogitrack.logitrack.dto.commandedto;
import lombok.Data;

@Data
public class UpdateStatutDTO {
    private String statut;

    public String getStatut() {
        return statut;
    }
}
