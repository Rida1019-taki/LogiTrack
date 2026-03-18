package org.elogitrack.logitrack.Repository;

import org.elogitrack.logitrack.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client , Long> {

}
