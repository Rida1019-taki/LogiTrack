package org.elogitrack.logitrack.service;

import org.elogitrack.logitrack.dto.lignecommandedto.LigneCommandeRequestDTO;
import org.elogitrack.logitrack.dto.lignecommandedto.LigneCommandeResponseDTO;
import org.elogitrack.logitrack.mapper.LigneCommandeMapper;
import org.elogitrack.logitrack.model.LigneCommande;
import org.elogitrack.logitrack.model.Produit;
import org.elogitrack.logitrack.repository.LigneCommandeRepository;
import org.elogitrack.logitrack.repository.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LigneCommandeService {

    private final LigneCommandeRepository ligneCommandeRepository;
    private final ProduitRepository produitRepository;
    private final LigneCommandeMapper ligneCommandeMapper;

    public LigneCommandeService(
            LigneCommandeRepository ligneCommandeRepository,
            ProduitRepository produitRepository,
            LigneCommandeMapper ligneCommandeMapper
    ) {
        this.ligneCommandeRepository = ligneCommandeRepository;
        this.produitRepository = produitRepository;
        this.ligneCommandeMapper = ligneCommandeMapper;
    }

    public List<LigneCommandeResponseDTO> getAllLignesCommande() {

        return ligneCommandeRepository.findAll()
                .stream()
                .map(ligneCommandeMapper::toResponseDTO)
                .toList();
    }

    public LigneCommandeResponseDTO getLigneCommandeById(Long id) {

        LigneCommande ligne = ligneCommandeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "LigneCommande introuvable"
                        ));

        return ligneCommandeMapper.toResponseDTO(ligne);
    }

    public LigneCommandeResponseDTO saveLigneCommande(
            LigneCommandeRequestDTO dto
    ) {

        Produit produit = produitRepository.findById(dto.getProduitId())
                .orElseThrow(() ->
                        new RuntimeException("Produit introuvable"));

        LigneCommande ligne =
                ligneCommandeMapper.toEntity(dto);

        ligne.setProduit(produit);

        ligne = ligneCommandeRepository.save(ligne);

        return ligneCommandeMapper.toResponseDTO(ligne);
    }

    public void deleteLigneCommande(Long id) {
        ligneCommandeRepository.deleteById(id);
    }
}