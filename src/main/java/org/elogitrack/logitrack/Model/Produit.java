package org.elogitrack.logitrack.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "produit")
@Getter
@Setter
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produit")
    private Long idProduit;

    @Column(name = "nom")
    private String nom;

    @Column(name = "categorie")
    private String categorie;

    @Column(name = "prix")
    private double prix;

    @Column(name = "quantity")
    private int quantity;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    private List<LigneCommande> ligneCommandes;
}
