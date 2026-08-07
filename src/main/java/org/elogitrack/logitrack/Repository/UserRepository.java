package org.elogitrack.logitrack.repository;

import org.elogitrack.logitrack.model.Produit;
import org.elogitrack.logitrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
