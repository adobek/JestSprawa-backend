package com.pwr.jestsprawa.repositories;

import com.pwr.jestsprawa.model.Issue;
import com.pwr.jestsprawa.model.IssueStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IssueStatusRepository extends JpaRepository<IssueStatus, Integer> {
    List<IssueStatus> findAll();
    List<IssueStatus> findAllByIssueId(@Param("issueId") int issueId);
    @Query(value="SELECT ss1.* FROM app.issuesstatuses ss1 LEFT JOIN app.issuesstatuses ss2" +
            " ON ss1.issue_id = ss2.issue_id" +
            " AND ss1.date < ss2.date WHERE ss2.date IS NULL AND ss1.issue_id = :issueId",nativeQuery = true)
    IssueStatus findByIssueId(@Param("issueId") int issueId);


}
