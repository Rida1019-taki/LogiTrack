package org.elogitrack.logitrack.Repository;

import org.elogitrack.logitrack.Model.Commande;
import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandeRepository extends JpaRepository<Commande , Long> {
    List<Commande> findByClientId(Long clientId);


    @Query("SELECT COUNT(c) FROM Commande c")
    long countCommandes();

    @Query("SELECT lc.produit.nom FROM LigneCommande lc GROUP BY lc.produit.id ORDER BY SUM(lc.quantite) DESC")
    List<String> findTopProduit();
}