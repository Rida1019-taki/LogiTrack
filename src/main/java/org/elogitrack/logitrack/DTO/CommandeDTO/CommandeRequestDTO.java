package org.elogitrack.logitrack.dto.commandedto;

import org.elogitrack.logitrack.dto.lignecommandedto.LigneCommandeRequestDTO;

import java.util.List;
import lombok.Data;

@Data
public class CommandeRequestDTO {
    private Long clientId;
    private List<LigneCommandeRequestDTO> produits;

    public Long getClientId() {
        return clientId;
    }
}
