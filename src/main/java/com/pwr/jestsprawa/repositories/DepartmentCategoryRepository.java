package com.pwr.jestsprawa.repositories;

import com.pwr.jestsprawa.model.Category;
import com.pwr.jestsprawa.model.Commune;
import com.pwr.jestsprawa.model.DepartmentCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentCategoryRepository extends JpaRepository<DepartmentCategory, Integer> {
    Optional<DepartmentCategory> findByCategoryAndDepartment_Institution_Commune(Category category, Commune commune);
}
