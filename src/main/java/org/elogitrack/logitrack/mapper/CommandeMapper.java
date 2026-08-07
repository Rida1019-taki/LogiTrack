package org.elogitrack.logitrack.mapper;

import org.elogitrack.logitrack.dto.commandedto.CommandeRequestDTO;
import org.elogitrack.logitrack.dto.commandedto.CommandeResponseDTO;
import org.elogitrack.logitrack.model.Commande;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = LigneCommandeMapper.class
)
public interface CommandeMapper {

    @Mapping(target = "client", ignore = true)
    @Mapping(target = "ligneCommanden", ignore = true)
    @Mapping(target = "idCommande", ignore = true)
    @Mapping(target = "dateCommande", ignore = true)
    @Mapping(target = "statut", ignore = true)
    Commande toEntity(CommandeRequestDTO dto);

    @Mapping(source = "client.idClient", target = "clientIdClient")
    @Mapping(source = "client.nom", target = "clientNom")
    CommandeResponseDTO toResponseDTO(Commande commande);
}