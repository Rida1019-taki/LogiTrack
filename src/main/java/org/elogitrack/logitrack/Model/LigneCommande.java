package org.elogitrack.logitrack.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.EnableMBeanExport;

@Entity
@Table(name = "LigneCommande")
@Getter
@Setter
public class LigneCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLigneCommande;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "produit_Id")
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "id_Commande")
    private Commande commande;
}
