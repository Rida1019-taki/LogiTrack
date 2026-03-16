package org.elogitrack.logitrack.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Client")
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;
    private String nom;
    private String email;
    private String telefone;
    private String ville;

    public Client() {
    }

    public Client(String nom, String email, String telefone, String ville) {
        this.nom = nom;
        this.email = email;
        this.telefone = telefone;
        this.ville = ville;
    }

    @OneToMany(mappedBy = "id_Commande" , cascade = CascadeType.ALL)
    private List<Commande> commandes;
}
