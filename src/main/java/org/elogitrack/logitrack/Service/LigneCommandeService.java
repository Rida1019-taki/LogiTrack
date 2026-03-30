package org.elogitrack.logitrack.service;

import org.elogitrack.logitrack.dto.lignecommandedto.LigneCommandeRequestDTO;
import org.elogitrack.logitrack.dto.lignecommandedto.LigneCommandeResponseDTO;
import org.elogitrack.logitrack.model.LigneCommande;
import org.elogitrack.logitrack.model.Produit;
import org.elogitrack.logitrack.repository.LigneCommandeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LigneCommandeService {

    private final LigneCommandeRepository ligneCommandeRepository;
    private final ModelMapper modelMapper;

    public LigneCommandeService(LigneCommandeRepository ligneCommandeRepository,
                                ModelMapper modelMapper) {
        this.ligneCommandeRepository = ligneCommandeRepository;
        this.modelMapper = modelMapper;
    }

    // GET ALL
    public List<LigneCommandeResponseDTO> getAllLignesCommande() {
        return ligneCommandeRepository.findAll()
                .stream()
                .map(l -> modelMapper.map(l, LigneCommandeResponseDTO.class))
                .toList();
    }

    public LigneCommandeResponseDTO getLigneCommandeById(Long id) {
        LigneCommande ligne = ligneCommandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LigneCommande introuvable"));

        return modelMapper.map(ligne, LigneCommandeResponseDTO.class);
    }

    public LigneCommandeResponseDTO saveLigneCommande(LigneCommandeRequestDTO dto) {

        LigneCommande ligne = modelMapper.map(dto, LigneCommande.class);

        ligne = ligneCommandeRepository.save(ligne);

        return modelMapper.map(ligne, LigneCommandeResponseDTO.class);
    }

    public void deleteLigneCommande(Long id) {
        ligneCommandeRepository.deleteById(id);
    }
}
