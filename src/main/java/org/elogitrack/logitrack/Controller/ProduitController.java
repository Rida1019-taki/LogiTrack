package org.elogitrack.logitrack.Controller;

import org.elogitrack.logitrack.Model.Produit;
import org.elogitrack.logitrack.Service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @GetMapping
    public List<Produit> getAllProduits() {
        return produitService.getAllProduits();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
        return produitService.getProduitById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Produit createProduit(@RequestBody Produit produit) {
        return produitService.saveProduit(produit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestBody Produit produitDetails) {
        return produitService.getProduitById(id)
                .map(produit -> {
                    produit.setNom(produitDetails.getNom());
                    produit.setCategorie(produitDetails.getCategorie());
                    produit.setPrix(produitDetails.getPrix());
                    produit.setQuantity(produitDetails.getQuantity());
                    return ResponseEntity.ok(produitService.saveProduit(produit));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        return produitService.getProduitById(id)
                .map(produit -> {
                    produitService.deleteProduit(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
