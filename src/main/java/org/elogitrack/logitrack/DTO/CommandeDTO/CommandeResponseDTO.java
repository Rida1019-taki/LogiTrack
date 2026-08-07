package org.elogitrack.logitrack.dto.commandedto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import org.elogitrack.logitrack.dto.lignecommandedto.LigneCommandeResponseDTO;

@Data
public class CommandeResponseDTO {
    private Long idCommande;
    private Long clientIdClient;
    private String clientNom;
    private LocalDate dateCommande;
    private String statut;
    private List<LigneCommandeResponseDTO> ligneCommanden;
}
