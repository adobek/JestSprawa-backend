package com.pwr.jestsprawa.repositories;

import com.pwr.jestsprawa.model.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {
}
