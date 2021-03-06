package com.pwr.jestsprawa.repositories;

import com.pwr.jestsprawa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findOneByEmailIgnoreCase(String email);
}
