package org.elogitrack.logitrack.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "commande")
@Getter
@Setter
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_commande")
    private Long idCommande;

    @Column(name = "date_commande")
    private LocalDate dateCommande;

    @Column(name = "statut")
    private String statut;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<LigneCommande> ligneCommande;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    public Commande() {
    }

    public Commande(LocalDate dateCommande, String statut) {
        this.dateCommande = dateCommande;
        this.statut = statut;
    }
}
