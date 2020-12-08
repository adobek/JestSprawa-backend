package com.pwr.jestsprawa.repositories;

import com.pwr.jestsprawa.model.Commune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommuneRepository extends JpaRepository<Commune, Integer> {
    Optional<Commune> findOneByNameIgnoreCase(String communeName);
    @Query(value="select c.* from app.departments d" +
            " join app.institutions i on i.id = d.institution_id" +
            " join app.communes c on c.id = i.commune_id" +
            " where d.id = :departmentId", nativeQuery = true)
    Optional<Commune> findByDepartmentId(@Param("departmentId") int departmentId);
}
