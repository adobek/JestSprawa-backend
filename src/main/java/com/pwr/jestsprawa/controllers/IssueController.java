package com.pwr.jestsprawa.controllers;

import com.pwr.jestsprawa.model.Issue;
import com.pwr.jestsprawa.repositories.IssuesRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IssueController {

    private final IssuesRepository issuesRepository;

    public IssueController(IssuesRepository issuesRepository) {
        this.issuesRepository = issuesRepository;
    }

    @GetMapping("/issues")
    public Iterable<Issue> getIssuesByStatusId(@RequestParam int statusId) {
        return issuesRepository.findAllByStatusId(statusId);
    }
    //TODO: probably add IssueDTO
}
