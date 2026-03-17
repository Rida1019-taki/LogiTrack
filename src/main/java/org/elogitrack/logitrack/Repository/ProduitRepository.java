package org.elogitrack.logitrack.Repository;

import org.elogitrack.logitrack.Model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit , Long> {
    List<Produit> findByCategorie(String categorie);

    List<Produit> findByPrix(double prix);

    @Query("SELECT p FROM Produit p WHERE p.quantity < 5")
    List<Produit> findProduitsLowStock();
}
