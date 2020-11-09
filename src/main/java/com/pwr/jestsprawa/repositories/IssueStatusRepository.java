package com.pwr.jestsprawa.repositories;

import com.pwr.jestsprawa.model.Issue;
import com.pwr.jestsprawa.model.IssueStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IssueStatusRepository extends CrudRepository<IssueStatus, Long> {
    List<IssueStatus> findAll();
}
