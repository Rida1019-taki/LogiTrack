package org.elogitrack.logitrack.Service;

import org.elogitrack.logitrack.Model.Commande;
import org.elogitrack.logitrack.Model.LigneCommande;
import org.elogitrack.logitrack.Model.Produit;
import org.elogitrack.logitrack.Repository.CommandeRepository;
import org.elogitrack.logitrack.Repository.LigneCommandeRepository;
import org.elogitrack.logitrack.Repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    @Autowired
    private ProduitRepository produitRepository;

    public Commande createCommande(Commande commande){
        commande.setDateCommande(LocalDate.now());
        commande.setStatut("EN ATTENTE");
        return commandeRepository.save(commande);
    }

    public LigneCommande addProduitToOrder(Long idOrder , Long idProduct , int quantity){
        Commande commande = commandeRepository.findById(idOrder).orElseThrow();
        Produit produit = produitRepository.findById(idProduct).orElseThrow();

        LigneCommande ligneCommande = new LigneCommande();
        ligneCommande.setCommande(commande);
        ligneCommande.setProduit(produit);
        ligneCommande.setQuantity(quantity);
        ligneCommande.setQuantity(produit.getQuantity() - quantity);
        produitRepository.save(produit);
        return ligneCommandeRepository.save(ligneCommande);
    }

    public Commande updateStatus(Long id , String status){
        Commande commande = commandeRepository.findById(id).orElseThrow();
        commande.setStatut(status);
        return commandeRepository.save(commande);
    }

    public List<Commande> getCommandeByIdClient(Long id){
        return commandeRepository.findCommandeByIdClient(id);
    }

    public long countCommande(){
        return commandeRepository.countCommandes();
    }

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id).orElseThrow();
    }

    public Commande saveCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }
}
