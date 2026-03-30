package org.elogitrack.logitrack.dto.produitdto;
import lombok.Data;

@Data
public class ProduitRequestDTO {
    private String nom;
    private String categorie;
    private Double prix;
    private Integer quantity;
}
