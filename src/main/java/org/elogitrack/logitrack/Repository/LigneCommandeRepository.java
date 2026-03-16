package org.elogitrack.logitrack.Repository;

import org.elogitrack.logitrack.Model.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande , Long> {
}
