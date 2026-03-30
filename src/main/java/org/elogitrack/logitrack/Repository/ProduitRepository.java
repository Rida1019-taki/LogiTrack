package org.elogitrack.logitrack.repository;

import org.elogitrack.logitrack.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit , Long> {
    List<Produit> findByCategorie(String categorie);

    List<Produit> findByPrixLessThan(double prix);

    @Query("SELECT p FROM Produit p WHERE p.quantity < :quantity")
    List<Produit> findProduitsLowStock(@Param("quantity") int quantity);
}
