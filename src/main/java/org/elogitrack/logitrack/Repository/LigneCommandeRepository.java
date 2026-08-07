package org.elogitrack.logitrack.repository;

import org.elogitrack.logitrack.model.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande , Long> {
    @Query(value = "SELECT id_produit FROM ligne_commandes GROUP BY id_produit ORDER BY SUM(quantity) DESC LIMIT 1", nativeQuery = true)
    Long findTopProduct();
}
