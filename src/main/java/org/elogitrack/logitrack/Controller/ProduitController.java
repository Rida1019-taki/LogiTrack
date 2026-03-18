package org.elogitrack.logitrack.Controller;

import org.elogitrack.logitrack.Model.Produit;
import org.elogitrack.logitrack.Service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProduitController {
    @Autowired
    private ProduitService produitService;

    @PostMapping
    public Produit addProdouit(Produit produit){
        return produitService.saveProduit(produit);
    }

    @GetMapping
    public List<Produit> getAllProduit(){
        return produitService.getAllProduits();
    }

    @GetMapping("/category/{category}")
    public List<Produit> getByCategorie(@PathVariable String categorie){
        return produitService.findProduitBycategorie(categorie);
    }

    @GetMapping("/prix/{prix}")
    public List<Produit> getProduitByPrix(double prix){
        return produitService.findProduitByPrix(prix);
    }

    @GetMapping("/low-stock")
    public List<Produit> getProduitLowStock(int quantity){
        return produitService.findProduitByLowStock(quantity);
    }

}
