package org.elogitrack.logitrack.Controller;

import org.elogitrack.logitrack.Model.Commande;
import org.elogitrack.logitrack.Model.LigneCommande;
import org.elogitrack.logitrack.Service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/ligneCommande")
public class CommandeController {
    @Autowired
    private CommandeService commandeService;

    @PostMapping
    public LigneCommande addCommande(Long idCommande , Long idClient , int quantity){
        return commandeService.addProduitToOrder(idCommande , idClient , quantity);
    }

    @PutMapping("/{id}/status")
    public Commande updateStatus(@PathVariable Long id , @RequestBody String status){
        return commandeService.updateStatus(id , status);
    }

    @GetMapping("/client/{clientId}")
    public List<Commande> getCommandeByClient(@PathVariable Long id){
        return commandeService.getCommandeByIdClient(id);
    }
}
