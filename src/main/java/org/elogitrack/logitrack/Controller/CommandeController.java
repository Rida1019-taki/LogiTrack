package org.elogitrack.logitrack.Controller;

import org.elogitrack.logitrack.Model.Commande;
import org.elogitrack.logitrack.Model.LigneCommande;
import org.elogitrack.logitrack.Model.Produit;
import org.elogitrack.logitrack.Service.CommandeService;
import org.elogitrack.logitrack.Service.StatistiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commande")
public class CommandeController {
    @Autowired
    private CommandeService commandeService;

    @Autowired
    private StatistiqueService statistiqueService;

    @PostMapping
    public Commande createCommande(@RequestBody Commande commande){
        return commandeService.createCommande(commande);
    }

    @GetMapping("/{id}")
    public Commande getCommandeById(@PathVariable Long id){
        return commandeService.getCommandeById(id);
    }

    @GetMapping
    public List<Commande> getAllCommandes(){
        return commandeService.getAllCommandes();
    }
    @PostMapping("/{idCommande}/products")
    public LigneCommande addCommande(@PathVariable Long idCommande , @RequestParam Long idClient , @RequestParam int quantity){
        return commandeService.addProduitToOrder(idCommande , idClient , quantity);
    }

    @PutMapping("/{id}/status")
    public Commande updateStatus(@PathVariable Long id , @RequestBody String status){
        return commandeService.updateStatus(id , status);
    }

    @GetMapping("/count")
    public long countCommande(){
        return commandeService.countCommande();
    }

    @GetMapping("/client/{id}")
    public List<Commande> getCommandeByClient(@PathVariable Long id){
        return commandeService.getCommandeByIdClient(id);
    }

    @GetMapping("/tpo-product")
    public Produit topProduit(){
        return statistiqueService.getTopProduit();
    }
}
