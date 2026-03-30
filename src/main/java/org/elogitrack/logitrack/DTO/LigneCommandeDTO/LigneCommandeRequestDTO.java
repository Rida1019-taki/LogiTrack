package org.elogitrack.logitrack.dto.lignecommandedto;
import lombok.Data;

@Data
public class LigneCommandeRequestDTO {
    private Long produitId;
    private Integer quantity;
}
