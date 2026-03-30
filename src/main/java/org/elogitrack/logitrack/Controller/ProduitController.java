package org.elogitrack.logitrack.controller;

import org.elogitrack.logitrack.dto.produitdto.ProduitRequestDTO;
import org.elogitrack.logitrack.dto.produitdto.ProduitResponseDTO;
import org.elogitrack.logitrack.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProduitController {
    @Autowired
    private ProduitService produitService;

    @PostMapping
    public ResponseEntity<ProduitResponseDTO> addProduit(@RequestBody ProduitRequestDTO produitDto){
        return new ResponseEntity<>(produitService.createProduit(produitDto),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProduitResponseDTO>> getAllProduits(){
        return ResponseEntity.ok(produitService.getAllProduits());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProduitResponseDTO>> getByCategorie(@PathVariable("category") String category){
        return ResponseEntity.ok(produitService.getProduitsByCategory(category));
    }

    @GetMapping("/prix/{prix}")
    public ResponseEntity<List<ProduitResponseDTO>> getProduitByPrix(@PathVariable double prix){
        return ResponseEntity.ok(produitService.getProduitsByPriceLessThan(prix));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<ProduitResponseDTO>> getProduitLowStock(@RequestParam int quantity){
        return ResponseEntity.ok(produitService.getLowStockProduits(quantity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable("id") Long id){
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/top-product")
    public ProduitResponseDTO topProduit() {
        return produitService.getTopProduit();
    }

}
