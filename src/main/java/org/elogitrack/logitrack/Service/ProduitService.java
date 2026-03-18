package org.elogitrack.logitrack.Service;

import org.elogitrack.logitrack.Model.Produit;
import org.elogitrack.logitrack.Repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Produit getProduitById(Long id) {
        return produitRepository.findById(id).orElseThrow();
    }

    public List<Produit> findProduitBycategorie(String categorie){
        return produitRepository.findByCategorie(categorie);
    }

    public List<Produit> findProduitByPrix(double prix){
        return produitRepository.findByPrixLessThan(prix);
    }

    public List<Produit> findProduitByLowStock(int quantity){
        return produitRepository.findProduitsLowStock(quantity);
    }

    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }
}
