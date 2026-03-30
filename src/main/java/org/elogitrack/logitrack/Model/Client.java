package org.elogitrack.logitrack.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
@SuppressWarnings("JpaDataSourceORMInspection")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long idClient;

    @Column(name = "nom")
    private String nom;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "ville")
    private String ville;

    public Client() {
    }

    public Client(String nom, String email, String telefone, String ville) {
        this.nom = nom;
        this.email = email;
        this.telefone = telefone;
        this.ville = ville;
    }

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Commande> commandes;
}
