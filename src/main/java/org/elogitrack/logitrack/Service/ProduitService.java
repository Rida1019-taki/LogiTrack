package org.elogitrack.logitrack.service;

import org.elogitrack.logitrack.dto.produitdto.ProduitRequestDTO;
import org.elogitrack.logitrack.dto.produitdto.ProduitResponseDTO;
import org.elogitrack.logitrack.mapper.ProduitMapper;
import org.elogitrack.logitrack.model.Produit;
import org.elogitrack.logitrack.repository.LigneCommandeRepository;
import org.elogitrack.logitrack.repository.ProduitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {

    private final ProduitRepository produitRepository;
    private final LigneCommandeRepository ligneCommandeRepository;
    private final ProduitMapper produitMapper;

    public ProduitService(
            ProduitRepository produitRepository,
            LigneCommandeRepository ligneCommandeRepository,
            ProduitMapper produitMapper
    ) {
        this.produitRepository = produitRepository;
        this.ligneCommandeRepository = ligneCommandeRepository;
        this.produitMapper = produitMapper;
    }

    public ProduitResponseDTO createProduit(ProduitRequestDTO dto) {

        Produit produit = produitMapper.toEntity(dto);

        produit = produitRepository.save(produit);

        return produitMapper.toResponseDTO(produit);
    }

    public Page<ProduitResponseDTO> getAllProduits(Pageable pageable) {

        return produitRepository.findAll(pageable)
                .map(produitMapper::toResponseDTO);
    }

    public ProduitResponseDTO updateProduit(
            Long id,
            ProduitRequestDTO dto
    ) {

        Produit produit = produitRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Produit introuvable"));

        produit.setNom(dto.getNom());
        produit.setPrix(dto.getPrix());
        produit.setQuantity(dto.getQuantity());
        produit.setCategorie(dto.getCategorie());

        produit = produitRepository.save(produit);

        return produitMapper.toResponseDTO(produit);
    }

    public List<ProduitResponseDTO> getProduitsByPrixExact(double prix) {

        return produitRepository.findByPrix(prix)
                .stream()
                .map(produitMapper::toResponseDTO)
                .toList();
    }

    public long countProduits() {
        return produitRepository.countProduits();
    }

    public ProduitResponseDTO getProduitById(Long id) {

        Produit produit = produitRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Produit introuvable"));

        return produitMapper.toResponseDTO(produit);
    }

    public List<ProduitResponseDTO> getProduitsByCategory(
            String categorie
    ) {

        return produitRepository.findByCategorie(categorie)
                .stream()
                .map(produitMapper::toResponseDTO)
                .toList();
    }

    public List<ProduitResponseDTO> getProduitsByPriceLessThan(
            double prix
    ) {

        return produitRepository.findByPrixLessThan(prix)
                .stream()
                .map(produitMapper::toResponseDTO)
                .toList();
    }

    public List<ProduitResponseDTO> getLowStockProduits(
            int quantity
    ) {

        return produitRepository.findProduitsLowStock(quantity)
                .stream()
                .map(produitMapper::toResponseDTO)
                .toList();
    }

    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }

    public ProduitResponseDTO getTopProduit() {

        Long produitId = ligneCommandeRepository.findTopProduct();

        if (produitId == null) {
            return null;
        }

        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() ->
                        new RuntimeException("Produit introuvable"));

        return produitMapper.toResponseDTO(produit);
    }
}