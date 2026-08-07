package org.elogitrack.logitrack.mapper;

import org.elogitrack.logitrack.dto.produitdto.ProduitRequestDTO;
import org.elogitrack.logitrack.dto.produitdto.ProduitResponseDTO;
import org.elogitrack.logitrack.model.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProduitMapper {

    Produit toEntity(ProduitRequestDTO dto);

    @Mapping(source = "idProduit", target = "id")
    ProduitResponseDTO toResponseDTO(Produit produit);
}