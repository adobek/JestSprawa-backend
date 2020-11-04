package com.pwr.jestsprawa.repositories;


import com.pwr.jestsprawa.model.Issue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssuesRepository extends CrudRepository<Issue, Integer> {
    @Query(value="SELECT i.* FROM app.issues AS i JOIN " +
            "(SELECT ss1.* FROM app.issuesstatuses ss1 LEFT JOIN app.issuesstatuses ss2 " +
            "ON ss1.issue_id = ss2.issue_id " +
            "AND ss1.date < ss2.date WHERE ss2.date IS NULL AND ss1.status_id = :status) ss3 " +
            "ON i.id = ss3.issue_id", nativeQuery = true)
    List<Issue> findAllByStatusId(@Param("status") int status);

    Optional<Issue> findIssueById(int id);

}
