package org.elogitrack.logitrack.dto.lignecommandedto;
import lombok.Data;

@Data
public class LigneCommandeResponseDTO {
    private Long idLigneCommande;
    private Long produitIdProduit; // Matches model produit.idProduit
    private String produitNom;     // Matches model produit.nom
    private Integer quantity;
}
