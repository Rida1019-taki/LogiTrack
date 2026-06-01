package org.elogitrack.logitrack.dto.lignecommandedto;
import lombok.Data;

@Data
public class LigneCommandeResponseDTO {
    private Long idLigneCommande;
    private Long produitIdProduit;
    private String produitNom;
    private Integer quantity;
}
