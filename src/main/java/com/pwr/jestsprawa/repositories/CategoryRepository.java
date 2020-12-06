package com.pwr.jestsprawa.repositories;

import com.pwr.jestsprawa.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Short> {
    List<Category> findAllByDepartments_Id(int departmentId);
}
