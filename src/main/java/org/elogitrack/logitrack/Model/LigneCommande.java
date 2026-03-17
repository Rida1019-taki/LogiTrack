package org.elogitrack.logitrack.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ligne_commande")
@Getter
@Setter
public class LigneCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLigneCommande;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "id_produit", nullable = false)
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "id_commande", nullable = false)
    private Commande commande;
}
