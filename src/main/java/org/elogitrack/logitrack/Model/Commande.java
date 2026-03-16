package org.elogitrack.logitrack.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.EnableMBeanExport;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Commande")
@Getter
@Setter
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommande;
    private LocalDate dateCommande;

    @Enumerated(EnumType.STRING)
    private StatutCommande statut;

    public Commande() {
    }

    public Commande(LocalDate dateCommande, StatutCommande statut) {
        this.dateCommande = dateCommande;
        this.statut = statut;
    }

    @OneToMany(mappedBy = "id_ligneCommande" , cascade = CascadeType.ALL)
    private List<LigneCommande> ligneCommande;

    @ManyToOne
    @JoinColumn(name = "id_Client")
    private Client client;
}
