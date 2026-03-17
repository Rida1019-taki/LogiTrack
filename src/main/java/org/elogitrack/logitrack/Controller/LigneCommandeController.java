package org.elogitrack.logitrack.Controller;

import org.elogitrack.logitrack.Model.LigneCommande;
import org.elogitrack.logitrack.Service.LigneCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lignes-commande")
public class LigneCommandeController {

    @Autowired
    private LigneCommandeService ligneCommandeService;

    @GetMapping
    public List<LigneCommande> getAllLignesCommande() {
        return ligneCommandeService.getAllLignesCommande();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LigneCommande> getLigneCommandeById(@PathVariable Long id) {
        return ligneCommandeService.getLigneCommandeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LigneCommande createLigneCommande(@RequestBody LigneCommande ligneCommande) {
        return ligneCommandeService.saveLigneCommande(ligneCommande);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LigneCommande> updateLigneCommande(@PathVariable Long id, @RequestBody LigneCommande ligneCommandeDetails) {
        return ligneCommandeService.getLigneCommandeById(id)
                .map(ligneCommande -> {
                    ligneCommande.setQuantity(ligneCommandeDetails.getQuantity());
                    ligneCommande.setProduit(ligneCommandeDetails.getProduit());
                    ligneCommande.setCommande(ligneCommandeDetails.getCommande());
                    return ResponseEntity.ok(ligneCommandeService.saveLigneCommande(ligneCommande));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLigneCommande(@PathVariable Long id) {
        return ligneCommandeService.getLigneCommandeById(id)
                .map(ligneCommande -> {
                    ligneCommandeService.deleteLigneCommande(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
