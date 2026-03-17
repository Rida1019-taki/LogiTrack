package org.elogitrack.logitrack.Repository;

import org.elogitrack.logitrack.Model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande , Long> {
    List<Commande> findByClient_IdClient(Long clientId);

    @Query("SELECT COUNT(c) FROM Commande c")
    long countCommandes();

    @Query("SELECT lc.produit.nom FROM LigneCommande lc GROUP BY lc.produit.idProduit, lc.produit.nom ORDER BY SUM(lc.quantity) DESC")
    List<String> findTopProduit();
}
