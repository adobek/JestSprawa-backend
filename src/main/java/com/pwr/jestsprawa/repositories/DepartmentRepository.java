package com.pwr.jestsprawa.repositories;

import com.pwr.jestsprawa.model.Commune;
import com.pwr.jestsprawa.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findByInstitution_CommuneAndCategories_Id(Commune commune, Short categoryId);
    List<Department> findAllByInstitution_Commune(Commune commune);
}
