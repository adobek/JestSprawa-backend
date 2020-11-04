package com.pwr.jestsprawa.repositories;

import com.pwr.jestsprawa.model.IssueStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueStatusRepository extends JpaRepository<IssueStatus, Integer> { }
