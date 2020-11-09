package com.pwr.jestsprawa.repositories;

import com.pwr.jestsprawa.model.Issue;
import com.pwr.jestsprawa.model.IssueStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueStatusRepository extends CrudRepository<IssueStatus, Long> {
    List<IssueStatus> findAll();

    List<IssueStatus> findAllByIssueId(@Param("issueId") int issueId);
}
