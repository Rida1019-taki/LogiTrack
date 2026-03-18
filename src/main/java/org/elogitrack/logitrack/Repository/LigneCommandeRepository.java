package org.elogitrack.logitrack.Repository;

import org.elogitrack.logitrack.Model.LigneCommande;
import org.elogitrack.logitrack.Model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande , Long> {
    @Query("SELECT l.produit FROM LigneCommande l GROUP BY l.produit ORDER BY SUM(l.quantity) DESC LIMIT 1")
    Produit findTopProduct();
}
