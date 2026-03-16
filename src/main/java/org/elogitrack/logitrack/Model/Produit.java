package org.elogitrack.logitrack.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Produit")
@Getter
@Setter
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduit;
    private String nom;
    private String categorie;
    private double prix;
    private int quantity;

    @OneToMany(mappedBy = "Id_produit" , cascade = CascadeType.ALL)
    private List<LigneCommande> ligneCommandes;
}
