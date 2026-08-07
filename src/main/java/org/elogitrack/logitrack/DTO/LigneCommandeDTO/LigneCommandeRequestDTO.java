package org.elogitrack.logitrack.dto.lignecommandedto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class LigneCommandeRequestDTO {

    @NotNull(message = "L'identifiant du produit est obligatoire")
    @Positive(message = "L'identifiant du produit doit être positif")
    private Long produitId;

    @NotNull(message = "La quantité est obligatoire")
    @Positive(message = "La quantité doit être supérieure à 0")
    private Integer quantity;
}