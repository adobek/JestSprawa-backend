package com.pwr.jestsprawa.repositories;

import com.pwr.jestsprawa.model.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends CrudRepository<Status, Short> {
    Optional<Status> findByNameIgnoreCase(String name);
}
