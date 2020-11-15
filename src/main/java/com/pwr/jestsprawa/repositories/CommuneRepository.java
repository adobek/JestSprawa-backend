package com.pwr.jestsprawa.repositories;

import com.pwr.jestsprawa.model.Commune;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommuneRepository extends JpaRepository<Commune, Integer> {
    Optional<Commune> findOneByNameIgnoreCase(String communeName);
}
