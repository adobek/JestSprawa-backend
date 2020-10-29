package com.pwr.jestsprawa.repositories;

import com.pwr.jestsprawa.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Short> {
    Role findByName(String name);
}
