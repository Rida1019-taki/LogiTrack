package org.elogitrack.logitrack.controller;

import org.elogitrack.logitrack.dto.produitdto.ProduitRequestDTO;
import org.elogitrack.logitrack.dto.produitdto.ProduitResponseDTO;
import org.elogitrack.logitrack.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProduitController {
    @Autowired
    private ProduitService produitService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<ProduitResponseDTO> addProduit(@RequestBody ProduitRequestDTO produitDto){
        return new ResponseEntity<>(produitService.createProduit(produitDto),HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    public ResponseEntity<Page<ProduitResponseDTO>> getAllProduits(Pageable pageable) {
        return ResponseEntity.ok(produitService.getAllProduits(pageable));
    }

    @GetMapping("/category/{category}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    public ResponseEntity<List<ProduitResponseDTO>> getByCategorie(@PathVariable("category") String category){
        return ResponseEntity.ok(produitService.getProduitsByCategory(category));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    public ResponseEntity<ProduitResponseDTO> getProduitById(@PathVariable Long id) {
        return ResponseEntity.ok(produitService.getProduitById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<ProduitResponseDTO> updateProduit(
            @PathVariable Long id,
            @RequestBody ProduitRequestDTO dto){

        return ResponseEntity.ok(produitService.updateProduit(id,dto));
    }

    @GetMapping("/count")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Long> countProduits(){
        return ResponseEntity.ok(produitService.countProduits());
    }


    @GetMapping("/prix/{prix}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    public ResponseEntity<List<ProduitResponseDTO>> getProduitByPrix(@PathVariable double prix){
        return ResponseEntity.ok(produitService.getProduitsByPriceLessThan(prix));
    }

    @GetMapping("/low-stock")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<List<ProduitResponseDTO>> getProduitLowStock(@RequestParam int quantity){
        return ResponseEntity.ok(produitService.getLowStockProduits(quantity));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduit(@PathVariable("id") Long id){
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/top-product")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    public ProduitResponseDTO topProduit() {
        return produitService.getTopProduit();
    }

}
