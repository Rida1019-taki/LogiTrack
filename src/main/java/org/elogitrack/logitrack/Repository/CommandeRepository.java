package org.elogitrack.logitrack.repository;


import org.elogitrack.logitrack.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    @Query("SELECT c FROM Commande c WHERE c.client.id = :clientId")
    List<Commande> findCommandeByIdClient(@Param("clientId") Long clientId);

    @Query("SELECT COUNT(c) FROM Commande c")
    long countCommandes();


    @Query("SELECT lc.produit.nom FROM LigneCommande lc GROUP BY lc.produit.idProduit, lc.produit.nom ORDER BY SUM(lc.quantity) DESC")
    List<String> findTopProduit();

    List<Commande> findByStatut(String statut);

    @Query("""
        SELECT COUNT(c)
        FROM Commande c
        WHERE c.statut = :statut
        """)
    long countByStatut(String statut);

    List<Commande> findTop5ByOrderByDateCommandeDesc();
}
