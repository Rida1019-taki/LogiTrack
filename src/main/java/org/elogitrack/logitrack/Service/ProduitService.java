package org.elogitrack.logitrack.service;

import org.elogitrack.logitrack.dto.produitdto.ProduitRequestDTO;
import org.elogitrack.logitrack.dto.produitdto.ProduitResponseDTO;
import org.elogitrack.logitrack.model.Produit;
import org.elogitrack.logitrack.repository.LigneCommandeRepository;
import org.elogitrack.logitrack.repository.ProduitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class ProduitService {

    private final ProduitRepository produitRepository;
    private final LigneCommandeRepository ligneCommandeRepository;
    private final ModelMapper modelMapper;

    public ProduitService(ProduitRepository produitRepository,
                          LigneCommandeRepository ligneCommandeRepository,
                          ModelMapper modelMapper) {
        this.produitRepository = produitRepository;
        this.ligneCommandeRepository = ligneCommandeRepository;
        this.modelMapper = modelMapper;
    }

    public ProduitResponseDTO createProduit(ProduitRequestDTO dto) {
        Produit produit = modelMapper.map(dto, Produit.class);
        produit = produitRepository.save(produit);
        return modelMapper.map(produit, ProduitResponseDTO.class);
    }

    public Page<ProduitResponseDTO> getAllProduits(Pageable pageable) {

        return produitRepository.findAll(pageable)
                .map(produit -> modelMapper.map(produit, ProduitResponseDTO.class));
    }

    public ProduitResponseDTO getProduitById(Long id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));

        return modelMapper.map(produit, ProduitResponseDTO.class);
    }

    public List<ProduitResponseDTO> getProduitsByCategory(String categorie){
        return produitRepository.findByCategorie(categorie)
                .stream()
                .map(p -> modelMapper.map(p, ProduitResponseDTO.class))
                .toList();
    }

    public List<ProduitResponseDTO> getProduitsByPriceLessThan(double prix){
        return produitRepository.findByPrixLessThan(prix)
                .stream()
                .map(p -> modelMapper.map(p, ProduitResponseDTO.class))
                .toList();
    }

    public List<ProduitResponseDTO> getLowStockProduits(int quantity){
        return produitRepository.findProduitsLowStock(quantity)
                .stream()
                .map(p -> modelMapper.map(p, ProduitResponseDTO.class))
                .toList();
    }

    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }

    public ProduitResponseDTO getTopProduit() {
        Long produitId = ligneCommandeRepository.findTopProduct();
        if(produitId == null) return null;

        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));

        return modelMapper.map(produit, ProduitResponseDTO.class);
    }
}
