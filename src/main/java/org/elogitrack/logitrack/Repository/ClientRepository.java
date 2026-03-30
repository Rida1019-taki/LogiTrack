package org.elogitrack.logitrack.repository;

import org.elogitrack.logitrack.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client , Long> {

}
