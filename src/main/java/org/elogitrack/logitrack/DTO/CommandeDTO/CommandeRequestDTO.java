package org.elogitrack.logitrack.dto.commandedto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.elogitrack.logitrack.dto.lignecommandedto.LigneCommandeRequestDTO;

import java.util.List;

@Data
public class CommandeRequestDTO {

    @NotNull(message = "Le client est obligatoire")
    @Positive(message = "L'identifiant du client doit être positif")
    private Long clientId;

    @NotEmpty(message = "La commande doit contenir au moins un produit")
    @Valid
    private List<LigneCommandeRequestDTO> produits;
}