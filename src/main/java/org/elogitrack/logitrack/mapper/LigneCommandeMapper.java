package org.elogitrack.logitrack.mapper;

import org.elogitrack.logitrack.dto.lignecommandedto.LigneCommandeRequestDTO;
import org.elogitrack.logitrack.dto.lignecommandedto.LigneCommandeResponseDTO;
import org.elogitrack.logitrack.model.LigneCommande;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LigneCommandeMapper {

    @Mapping(target = "produit", ignore = true)
    @Mapping(target = "commande", ignore = true)
    LigneCommande toEntity(LigneCommandeRequestDTO dto);

    @Mapping(source = "produit.idProduit", target = "produitIdProduit")
    @Mapping(source = "produit.nom", target = "produitNom")
    LigneCommandeResponseDTO toResponseDTO(LigneCommande ligneCommande);
}